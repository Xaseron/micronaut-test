package io.micronaut.test.kotest

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import javax.inject.Singleton

@Controller("/greet")
class GreeterController(private val greeterService: GreeterServiceImpl) {


    @Get("/")
    fun index(): String {
        return greeterService.greet
    }

}

// comment this block and uncomment the example below to have a working example
@Singleton
open class GreeterServiceImpl {
     val greet = "Hello"
}


// uncomment the following lines to have a working example
//
//interface GreeterService {
//    val greet: String
//}
//
//@Singleton
//open class GreeterServiceImpl : GreeterService {
//    override val greet = "Hello"
//}
