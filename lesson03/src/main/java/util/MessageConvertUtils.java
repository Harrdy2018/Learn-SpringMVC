package util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;

public class MessageConvertUtils implements MessageSourceAware {
    private MessageSource messageSource;

    public void readLocaleSpecificMessage()
    {
        String englishMessage = messageSource.getMessage("first.name", null, Locale.US);
        System.out.println("First name label in English : " + englishMessage);
        String chineseMessage = messageSource.getMessage("first.name", null, Locale.SIMPLIFIED_CHINESE);
        System.out.println("First name label in Chinese : " + chineseMessage);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
