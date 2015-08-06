package com.cy.swp.common.springex;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import java.util.Map;

/**
 * Created by Richie.Lee on 2014/9/10.
 */
public class NoTitleMappingJacksonJsonView extends MappingJacksonJsonView {
    @Override
    protected Object filterModel(Map<String, Object> model) {
        Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
        if (result.size() == 1) {
            return result.values().iterator().next();
        } else {
            return result;
        }
    }
}
