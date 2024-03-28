package com.bolsadeideas.springboot.app.view.json;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

@Component("list.json")
@SuppressWarnings("unchecked")
public class CustomerListJsonView extends MappingJackson2JsonView {
    @Override
    protected Object filterModel(Map<String, Object> model) {
        model.remove("title");
        model.remove("page");

        Page<Customer> customers = (Page<Customer>) model.get("customers");
        model.remove("customers");

        model.put("customers", customers.getContent());

        return super.filterModel(model);
    }
}
