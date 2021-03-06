package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Administracion extends BasePage {

    private By materiaNameInputBy = By.id("inputPassword2");
    private By crearMateriaBtnBy = By.xpath("//button[contains(text(),'Crear')]");
    private By asignarDocenteBtnBy = By.xpath("//button[contains(text(),'Asignar')]");
    private By docenteUsernameInputBy = By.id("inputPassword3");

    public Administracion(WebDriver driver) {
        super(driver);
        driver.get("http://localhost:3000/administracion");
        wait.until(visibilityOfElementLocated(materiaNameInputBy));
    }

    public Administracion crearMateria(String nombre){
        driver.findElement(materiaNameInputBy).sendKeys(nombre);
        driver.findElement(crearMateriaBtnBy).click();
        return new Administracion(driver);
    }

    public boolean estaLaMateriaEnElMenu(String nombre){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[contains(text(),'"+nombre+"')]")));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public void agregarAdministradorAMateria(String username, String nombreMateria) {
        By materiaBy = By.xpath("//option[contains(text(),'"+nombreMateria+"')]");
        driver.findElement(materiaBy).click();
        driver.findElement(docenteUsernameInputBy).sendKeys(username);
        driver.findElement(asignarDocenteBtnBy).click();
    }
}
