package com.bolsadeideas.springboot.app.view.xml;

import com.bolsadeideas.springboot.app.models.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("list.xml")
public class CustomerListXmlView extends MarshallingView {

    @Autowired
    public CustomerListXmlView(Jaxb2Marshaller marshaller) {
        super(marshaller);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //They are removed because we don't need them
        model.remove("title");
        model.remove("page");

        Page<Customer> customers = (Page<Customer>) model.get("customers");
        model.remove("customers");

        model.put("customerList", new CustomerList(customers.getContent()));

        super.renderMergedOutputModel(model, request, response);
    }
}
