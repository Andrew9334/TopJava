package ru.javawebinar.topjava.web;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.io.UnsupportedEncodingException;

public class JsonTest {
    public static String getContent(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions actions, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(actions.andReturn()), clazz);
    }
}
