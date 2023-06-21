package rambler;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RamblerTopicsParametrizedTest extends BaseTest {
    static Stream<Arguments> getTopicCategories() {
        return Stream.of(
                Arguments.of("Новости", List.of("В мире", "Новости Москвы", "Политика", "Общество", "Происшествия", "Наука и техника", "Шоу-бизнес", "Военные новости", "Аналитика", "Игры")),
                Arguments.of("Женский", List.of("Звёзды", "Психология", "Еда", "Любовь", "Здоровье", "Красота", "Мода", "Дети", "Дом и сад"))
        );
    }

    @ParameterizedTest(name = "Соответствие списка категорий заданному топику {0}")
    @MethodSource("getTopicCategories")
    public void categoryShouldBeOpenAfterClick(String categoryName, List<String> buttonName) {
        open("https://www.rambler.ru/");
        $(".rc-topline").$(byText(categoryName)).click();
        $$("._3Ufez li").filter(visible).shouldHave(CollectionCondition.texts(buttonName));
    }
    
}
