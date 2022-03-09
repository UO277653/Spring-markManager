package com.uniovi.sdi2122212spring;

import com.uniovi.sdi2122212spring.pageobjects.*;
import com.uniovi.sdi2122212spring.util.SeleniumUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Sdi2122212SpringApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";

    //static String Geckodriver = "C:\\Dev\\tools\\selenium\\geckodriver-v0.30.0-win64.exe";

    static String Geckodriver = "C:\\Users\\adria\\OneDrive\\Escritorio\\UNIVERSIDAD\\AÑO 3\\SEMESTRE 2\\Sistemas Distribuidos e Internet\\Laboratorio\\Lab5\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
    //Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8090";
    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp(){
        driver.navigate().to(URL);
    }
    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }
    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {}
    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(1)
    void PR01A() {
        PO_HomeView.checkWelcomeToPage(driver, PO_Properties.getSPANISH());
    }

    @Test
    @Order(2)
    void PR01B() {
        List<WebElement> welcomeMessageElement = PO_HomeView.getWelcomeMessageText(driver,
                PO_Properties.getSPANISH());
        Assertions.assertEquals(welcomeMessageElement.get(0).getText(),
                PO_HomeView.getP().getString("welcome.message", PO_Properties.getSPANISH()));
    }

    //PR02. Opción de navegación. Pinchar en el enlace Registro en la página home
    @Test
    @Order(3)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
    }

    //PR03. Opción de navegación. Pinchar en el enlace Identifícate en la página home
    @Test
    @Order(4)
    public void PR03() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
    }

    //PR04. Opción de navegación. Cambio de idioma de Español a Inglés y vuelta a Español
    @Test
    @Order(5)
    public void PR04() {
        PO_HomeView.checkChangeLanguage(driver, "btnSpanish", "btnEnglish",
                PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
    }

    //PR05. Prueba del formulario de registro. registro con datos correctos
    @Test
    @Order(6)
    public void PR05() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_SignUpView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    //PR06A. Prueba del formulario de registro. DNI repetido en la BD
    // Propiedad: Error.signup.dni.duplicate
    @Test
    @Order(7)
    public void PR06A() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.duplicate",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de DNI repetido.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.duplicate",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    //PR06B. Prueba del formulario de registro. Nombre corto.
    // Propiedad: Error.signup.dni.length
    @Test
    @Order(8)
    public void PR06B() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.name.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de Nombre corto de nombre corto .
        String checkText = PO_HomeView.getP().getString("Error.signup.name.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(10)
    public void PR06C() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999999999999999999999999999990B", "Pablo", "Perez", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.dni.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de dni corto.
        String checkText = PO_HomeView.getP().getString("Error.signup.dni.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(11)
    public void PR06D() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Pablo", "Cal", "77777", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.lastName.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de apellido corto.
        String checkText = PO_HomeView.getP().getString("Error.signup.lastName.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(12)
    public void PR06E() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Pablo", "Pérez", "77", "77");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.password.length",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de contraseña corta.
        String checkText = PO_HomeView.getP().getString("Error.signup.password.length",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(13)
    public void PR06F() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_SignUpView.fillForm(driver, "99999990B", "Pablo", "Pérez", "77778", "77777");
        List<WebElement> result = PO_SignUpView.checkElementByKey(driver, "Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH() );
        //Comprobamos el error de contraseñas que no coinciden
        String checkText = PO_HomeView.getP().getString("Error.signup.passwordConfirm.coincidence",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

    @Test
    @Order(14)
    public void PR07() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(14)
    public void PR08() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        List<WebElement> markMenu = SeleniumUtils.waitLoadElementsBy(driver, "free", "//*[@id=\"navbarDropdown\"]", PO_View.getTimeout());

        markMenu.get(0).click();

        List<WebElement> markOptions = SeleniumUtils.waitLoadElementsBy(driver, "free", "/html/body/nav/div/div[1]/ul/li[2]/div/a", PO_View.getTimeout());

        Assertions.assertEquals(2, markOptions.size()); // Comprobamos que tiene al ser profesor tiene 2 opciones en el menu de gestion de notas
    }

    @Test
    @Order(15)
    public void PR09() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999988F", "123456");

        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        List<WebElement> leftOptions = SeleniumUtils.waitLoadElementsBy(driver, "free", "/html/body/nav/div/div[1]/ul/li", PO_View.getTimeout());

        Assertions.assertEquals(4, leftOptions.size()); // Comprobamos que tiene al ser admin tiene 4 opciones en la parte izquierda de la barra de navegacion
    }

    @Test
    @Order(16)
    public void PR10() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario con un nombre de usuario que no existe
        PO_LoginView.fillLoginForm(driver, "89999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Identifícate"; // El login no funcionará y se redigirá a la misma página
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());
    }

    @Test
    @Order(17)
    public void PR11() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //Comprobamos que entramos en la pagina privada de Alumno
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    //PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de estudiante
    @Test
    @Order(18)
    public void PR12() {
        //Vamos al formulario de logueo.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        //Rellenamos el formulario
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        //COmprobamos que entramos en la pagina privada de Alumno
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //Contamos el número de filas de notas
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(4, markList.size());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registro
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    //PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
    @Test
    @Order(19)
    public void PR13() {
        //Comprobamos que entramos en la pagina privada de Alumno
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999990A", "123456");
        String checkText = "Notas del usuario";
        List<WebElement> result = PO_View.checkElementBy(driver, "text", checkText);
        //SeleniumUtils.esperarSegundos(driver, 1);
        //Contamos las notas
        By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
        driver.findElement(enlace).click();
        //Esperamos por la ventana de detalle
        checkText = "Detalles de la nota";
        result = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText,result.get(0).getText());
        //Ahora nos desconectamos comprobamas que aparece el menu de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    //P14. Loguearse como profesor y Agregar Nota A2.
    //P14. Esta prueba podría encapsularse mejor ...
    @Test
    @Order(20)
    public void PR14() {
        //Vamos al formulario de login.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        //Cmmprobamos que entramos en la pagina privada del Profesor
        PO_View.checkElementBy(driver, "text", "99999993D");
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
        elements.get(0).click();
        //Esperamos a que aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'mark/add')]");
        //Pinchamos en agregar Nota.
        elements.get(0).click();
        //Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
        String checkText = "Nota Nueva 1";
        PO_PrivateView.fillFormAddMark(driver, 3, checkText, "8");
        //Esperamos a que se muestren los enlaces de paginación de la lista de notas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        //Nos vamos a la última página
        elements.get(3).click();
        //Comprobamos que aparece la nota en la página
        elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());
        //Ahora nos desconectamos y comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }

    @Test
    @Order(21)
    public void PR15() {
        //Vamos al formulario de login.
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "99999993D", "123456");
        //Comprobamos que entramos en la página privada del Profesor
        PO_View.checkElementBy(driver, "text", "99999993D");
        //Pinchamos en la opción de menú de Notas: //li[contains(@id, 'marks-menu')]/a
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
        elements.get(0).click();
        //Pinchamos en la opción de lista de notas.
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'mark/list')]");
        elements.get(0).click();
        //Esperamos a que se muestren los enlaces de paginación la lista de notas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        //Nos vamos a la última página
        elements.get(3).click();
        //Esperamos a que aparezca la Nueva nota en la última página
        //Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
        elements = PO_View.checkElementBy(driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
        elements.get(0).click();
        //Volvemos a la última página
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@class, 'page-link')]");
        elements.get(3).click();
        //Y esperamos a que NO aparezca la última "Nueva Nota 1"
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, "Nota Nueva 1",PO_View.getTimeout());
        //Ahora nos desconectamos comprobamos que aparece el menú de registrarse
        String loginText = PO_HomeView.getP().getString("signup.message", PO_Properties.getSPANISH());
        PO_PrivateView.clickOption(driver, "logout", "text", loginText);
    }
}
