package io.github.mat3e.hello;

import io.github.mat3e.hello.HelloService;
import io.github.mat3e.lang.Lang;
import io.github.mat3e.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACKO_ID_WELCOME = "Hello";


    @Test
    public void test_prepareGreeting_nullName_returnsGreetingWithFallbackName() throws Exception {
        //given
        LangRepository mockRepository = allwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);

        // when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_name_returnsGreetingWithNamee() throws Exception {
        //given
        var mockRepository = allwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        var name = "Mistrz";

        //when
        var result = SUT.prepareGreeting(name, -1);

        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACKO_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }
    /*@Test
    public void test_prepareGreeting_textlLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACKO_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }*/
    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() throws Exception {
        //given
        var mockRepository = new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        //when
        var result = SUT.prepareGreeting(null, -1);

        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
                @Override
                public Optional<Lang> findById(Integer id) {
                    if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                        return Optional.of(new Lang(null, FALLBACKO_ID_WELCOME, null));
                    }
                    return Optional.empty();
                }
            };
    }

    private LangRepository allwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

}