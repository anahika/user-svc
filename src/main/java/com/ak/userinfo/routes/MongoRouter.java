package com.ak.userinfo.routes;

import com.ak.userinfo.processor.UserMongoProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MongoRouter extends RouteBuilder {

    private final UserMongoProcessor userMongoProcessor;

    @Override
    public void configure() throws Exception {
        from("direct:findById")
                .bean(userMongoProcessor, "getById")
                .end();

        from("direct:findAll")
                .bean(userMongoProcessor, "getAll")
                .end();

        from("direct:addUser")
                .bean(userMongoProcessor, "addUser")
                .end();
    }
}
