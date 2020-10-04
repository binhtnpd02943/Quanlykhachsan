package edu.poly.fpt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.poly.fpt.services.TaikhoanServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TaikhoanServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
    	  // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)// Cung cáp userservice cho spring security
        .passwordEncoder(passwordEncoder());// cung cấp password encoder
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
                .antMatchers("/view/profile/**").permitAll()
                
                .antMatchers("/hotels/**").authenticated()// Tất cả các request khác đều cần phải xác thực mới được truy cập
                .antMatchers("/services/**").authenticated()
                .antMatchers("/citys/**").authenticated()
                .antMatchers("/types/**").authenticated()
                .antMatchers("/rooms/**").authenticated()
                .antMatchers("/users/**").authenticated()
                
                .and()
                .formLogin() // Cho phép người dùng xác thực bằng form login
                .loginPage("/login")
                .usernameParameter("tentaikhoan")
                .passwordParameter("matkhau")
                .defaultSuccessUrl("/home/", true)
                .failureUrl("/home/?error")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

}
