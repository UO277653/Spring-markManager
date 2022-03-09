package com.uniovi.sdi2122212spring.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView {

    static public void fillLoginForm(WebDriver driver, String dni, String passwordp){
        WebElement actualDni = driver.findElement(By.name("username"));
        actualDni.click();
        actualDni.clear();
        actualDni.sendKeys(dni);

        WebElement password = driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}
