package com.example.auth.web.vo.page;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPage extends BasePage{

    private String phone;

    private String realName;
}
