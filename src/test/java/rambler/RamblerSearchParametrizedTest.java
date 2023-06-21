package rambler;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class RamblerSearchParametrizedTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"qa.guru", "skillbox"})
    @DisplayName("Проверка правильности отображения результатов поиска")
    public void searchObjectShouldBeFined(String searchItem) {
        open("https://www.rambler.ru/");
        $("[name='query']").setValue(searchItem).pressEnter();
        switchTo().window(1);
        $("#client").shouldHave(Condition.text(searchItem));
        closeWindow();
    }

    @ParameterizedTest( name = "В результатах поиска присутствует \"{1}\" при поиске по \"{0}\"")
    @CsvFileSource (resources = "/items.csv")
    @DisplayName("Проверка автозамены в поиске при вводе в другой раскладке")
    public void searchObjectShouldBeFinedByAutocorrect(String searchItem, String expectedItem) {
        open("https://www.rambler.ru/");
        $("[name='query']").setValue(searchItem).pressEnter();
        switchTo().window(1);
        $("#client").shouldHave(Condition.text(expectedItem));
        closeWindow();
    }


}
