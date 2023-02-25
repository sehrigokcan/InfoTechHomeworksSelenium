//package stepdefinitions;
//
//import io.cucumber.java.After;
//import io.cucumber.java.AfterStep;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import utilities.Driver;
//
//public class Hooks {
//
//    static int stepCount;
//
//    @Before
//    public void setup() {
//        Driver.getDriver();
//    }
//
//    @After
//    public void teardown(Scenario scenario) {
//        // Aşağıdaki kod ile aldığımız SCREENSHOT'lar çok fazla yer kaplar.
//        // Onun için bu resimler GitHub'a Push edilmemelidir.
//        // Bunu şu şekilde yaparız;
//        // test-output klasörüne sağ tıklayıp en aşağıdaki 'Git' seçeneğine gelinir ve 'Add to .gitignore' seçilir.
//        // Karşınıza çıkan diğer sorulara da olumlu cevap verebilirsiniz.
//        // Böylece GitHub hesabına push etseniz bile resimler yollanmaz. Bunu istediğiniz diğer klasörlere de yapabilirsiniz.
//        byte[] picture;
//        if (scenario.isFailed()) { // Burada eğer bir testimiz fail ederse Screenshot alabiliyoruz.
//            picture = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(picture, "image/png", "failed" + scenario.getName());
//        }
//        else { // Burada eğer testimiz fail etmese bile Screenshot alabiliyoruz. Onun için aşağıdaki else kodu içeriğinin yorum olarak kalmasında fayda var.
//            picture = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(picture, "image/png", "passed" + scenario.getName());
//        }
//        Driver.closeDriver();
//    }
//
//    @AfterStep
//    public void makeSlowRunning() throws InterruptedException {
//        Driver.wait(1);
//        this.stepCount = stepCount + 1;
//        System.out.println((stepCount) + ". STEP");
//    }
//}