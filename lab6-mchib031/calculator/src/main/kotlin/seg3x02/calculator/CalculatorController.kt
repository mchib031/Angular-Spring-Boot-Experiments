package seg3x02.calculator

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("calculator")

class CalculatorController {
    @GetMapping("/add/{nombre1}/{nombre2}")
    fun getAddition(@PathVariable("nombre1") nombre1: Double,
                    @PathVariable("nombre2") nombre2: Double)
    = nombre1 + nombre2

    @GetMapping("/subtract/{nombre1}/{nombre2}")
    fun getSubstration(@PathVariable("nombre1") nombre1: Double,
                       @PathVariable("nombre2") nombre2: Double)
            = nombre1 - nombre2

    @GetMapping("/multiply/{nombre1}/{nombre2}")
    fun getMultiplication(@PathVariable("nombre1") nombre1: Double,
                          @PathVariable("nombre2") nombre2: Double)
            = nombre1 * nombre2

    @GetMapping("/divide/{nombre1}/{nombre2}")
    fun getDivision(@PathVariable("nombre1") nombre1: Double,
                    @PathVariable("nombre2") nombre2: Double)
            = nombre1 / nombre2
}