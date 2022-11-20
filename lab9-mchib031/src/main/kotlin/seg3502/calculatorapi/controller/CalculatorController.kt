package seg3502.calculatorapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("calculator")
class CalculatorController {
  @GetMapping("/add/{number1}/{number2}")
  fun getAddition(@PathVariable number1: Double, @PathVariable number2: Double) = (number1 + number2)

  @GetMapping("/subtract/{number1}/{number2}")
  fun getSubtraction(@PathVariable number1: Double, @PathVariable number2: Double) = (number1 - number2)

  @GetMapping("/multiply/{number1}/{number2}")
  fun getMultiplication(@PathVariable number1: Double, @PathVariable number2: Double) = (number1 * number2)

  @GetMapping("/divide/{number1}/{number2}")
  fun getDivision(@PathVariable number1: Double, @PathVariable number2: Double) = (number1 / number2)
}