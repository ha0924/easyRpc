package com.he.example.provider;


import com.he.example.common.service.UserService;
import com.he.herpc.registry.LocalRegistry;
import com.he.herpc.server.HttpServer;
import com.he.herpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 提供服务
        HttpServer httpServer= new VertxHttpServer();

        httpServer.doStart(8080);

    }
}
