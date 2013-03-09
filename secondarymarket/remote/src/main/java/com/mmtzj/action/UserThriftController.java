package com.mmtzj.action;

import com.mmtzj.handler.UserServiceHandler;
import com.mmtzj.thrift.gen.UserService;
import org.apache.thrift.TProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-3-8
 * Time: 上午10:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/user")
public class UserThriftController extends BaseThriftController {

    public UserThriftController() {
    }

    public UserThriftController(TProcessor processor) {
        super(new UserService.Processor<UserServiceHandler>(new UserServiceHandler()));
    }


}
