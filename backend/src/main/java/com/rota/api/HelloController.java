package com.rota.api;

import com.rota.api.dto.HelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "A dummy hello world greeter. Replace with something clever")
public class HelloController {
  private AtomicLong id = new AtomicLong();
  private String template = "Hello %s!";

  /**
   * /hello will return "Hello World!".
   * /hello?message=foo will return "Hello foo!".
   * @param message Message to print
   * @return HelloWorld response DTO
   */
  @GetMapping("/hello")
  @ApiOperation(value = "Basic hello world endpoint", response = HelloWorld.class)
  public HelloWorld helloEndpoint(
      @ApiParam(value = "Message to send (the response will be 'Hello message!')")
      @RequestParam(value = "message",
          defaultValue = "World")
      String message
  ) {
    return new HelloWorld(
        id.incrementAndGet(),
        String.format(template, message)
    );
  }

  /**
   * /hello/{message} endpoint, will return "Hello {message}!".
   * @param message message to send
   * @return HelloWorld DTO
   */
  @GetMapping("/hello/{message}")
  @ApiOperation(value = "A variable hello world endpoint.", response = HelloWorld.class)
  public HelloWorld helloVariableEndpoint(
      @ApiParam(value = "Message to send (the response will be 'Hello message!')", required = true)
      @PathVariable
      String message
  ) {
    return new HelloWorld(
        id.incrementAndGet(),
        String.format(template, message)
    );
  }
}
