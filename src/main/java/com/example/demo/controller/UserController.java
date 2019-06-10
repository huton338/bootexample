package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.form.UserForm;

/**
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String FORM_NAME = "userForm";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * ユーザ情報表示.
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public String showUserAllList(Model model) {

        List<UserEntity> userList = userRepository.findAll();

        model.addAttribute("list", userList);

        return "User/userlist";
    }

    /**
     * User ModelAttribute.
     *
     * @param form
     * @return
     */
    @ModelAttribute
    public UserForm setUserForm(@RequestAttribute(value = FORM_NAME, required = false) UserForm form) {

        // @ModelAttributeはformをでメソッドを判別後、返却値をModelに設定
        // @RequestAttributeはリクエストの中から値を抽出

        if (Objects.isNull(form)) {
            return new UserForm();
        }

        return form;
    }

    /**
     * ユーザ登録画面表示.
     *
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String createUserForm(@ModelAttribute UserForm form, Model model) {

        model.addAttribute(FORM_NAME, form);
        return "User/create";

    }

    /**
     * ユーザDB登録.
     *
     * @param form
     * @param result
     * @param model
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create/comit", params = "user_create")
    public String createUser(@ModelAttribute @Validated UserForm form, BindingResult result, Model model,
                    RedirectAttributes redirectAttrs) {
        // paramsはボタン名に対応する

        if (result.hasErrors()) {

            for (FieldError fieldError : result.getFieldErrors()) {
                model.addAttribute("message", fieldError.getDefaultMessage());
            }

            return "User/create";
        }

        redirectAttrs.addFlashAttribute("message", "insertに失敗しました");

        // DB insert
        if (userService.saveUser(form)) {
            redirectAttrs.addFlashAttribute("message", "登録成功");

        }

        return "redirect:/user/all";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public String updateUser(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttrs) {

        if (id == null) {
            redirectAttrs.addFlashAttribute("message", "パラメータが不正です。");
            return "redirect:/user/all";
        }

        UserEntity entity = userService.selectByIdUser(id);
        UserForm userForm = new UserForm();
        userForm.setId(entity.getId());
        userForm.setName(entity.getName());
        userForm.setDisplayName(entity.getDisplayName());
        userForm.setMailaddress(entity.getMailaddress());

        model.addAttribute(FORM_NAME, userForm);

        return "User/update";

    }

    /**
     * ユーザ更新.
     *
     * @param form
     * @param result
     * @param model
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update/comit", params = "user_update")
    public String updateUser(@ModelAttribute @Validated UserForm form, BindingResult result, Model model,
                    RedirectAttributes redirectAttrs) {
        // paramsはボタン名に対応する

        if (result.hasErrors()) {

            for (FieldError fieldError : result.getFieldErrors()) {
                model.addAttribute("message", fieldError.getDefaultMessage());
            }

            return "User/userlist";
        }

        redirectAttrs.addFlashAttribute("message", "updateに失敗しました");

        // DB insert
        if (userService.saveUser(form)) {
            redirectAttrs.addFlashAttribute("message", "更新成功");

        }

        return "redirect:/user/all";

    }

    /**
     * ユーザ削除
     *
     * @param id
     * @param model
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete", params = "user_delete")
    public String deleteUser(@RequestParam("id") Integer id, Model model,
                    RedirectAttributes redirectAttrs) {

        // form無しのパラメータ渡し方
        // https://stackoverflow.com/questions/41467779/how-to-pass-only-string-in-thymeleaf-form

        // DB delete
        if (userService.deleteUser(id)) {
            redirectAttrs.addFlashAttribute("message", "削除成功");
        } else {
            // paramsはボタン名に対応する
            redirectAttrs.addFlashAttribute("message", "存在しないIDです");
        }

        return "redirect:/user/all";

    }

}
