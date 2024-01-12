package com.westay.live.modules.security.controller;

import com.westay.common.exception.ErrorCode;
import com.westay.common.exception.RenException;
import com.westay.common.utils.IpUtils;
import com.westay.common.utils.Result;
import com.westay.common.validator.AssertUtils;
import com.westay.common.validator.ValidatorUtils;
import com.westay.live.modules.admin.dto.UserDTO;
import com.westay.live.modules.admin.service.UserService;
import com.westay.live.modules.log.entity.LoginLog;
import com.westay.live.modules.log.enums.LoginOperationEnum;
import com.westay.live.modules.log.enums.LoginStatusEnum;
import com.westay.live.modules.log.service.SysLogLoginService;
import com.westay.live.modules.security.dto.LoginDTO;
import com.westay.live.modules.security.password.PasswordUtils;
import com.westay.live.modules.security.service.CaptchaService;
import com.westay.live.modules.security.service.SysUserTokenService;
import com.westay.live.modules.security.user.SecurityUser;
import com.westay.live.modules.security.user.UserDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.westay.live.modules.admin.enums.UserStatusEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@Api(tags = "login")
@AllArgsConstructor
public class LoginController {

    private final UserService sysUserService;
    private final SysUserTokenService sysUserTokenService;
    private final CaptchaService captchaService;
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("captcha")
    @ApiOperation(value = "验证码", produces = "application/octet-stream")
    @ApiImplicitParam(paramType = "query", dataType = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid不能为空
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        //生成验证码
        captchaService.create(response, uuid);
    }


    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //效验数据
        ValidatorUtils.validateEntity(login);

        //验证码是否正确
        boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
        if (!flag) {
            return new Result().error(ErrorCode.CAPTCHA_ERROR);
        }

        //用户信息
        UserDTO user = sysUserService.getByUsername(login.getUsername());

        LoginLog log = new LoginLog();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        //用户不存在
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //密码错误
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //账号停用
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_DISABLE);
        }

        //登录成功
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("logout")
    @ApiOperation(value = "退出")
    public Result logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //退出
        sysUserTokenService.logout(user.getId());

        //用户信息
        LoginLog log = new LoginLog();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new Result();
    }

}
