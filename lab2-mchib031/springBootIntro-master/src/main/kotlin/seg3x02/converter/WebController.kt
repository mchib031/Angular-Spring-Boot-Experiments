package seg3x02.converter

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {
    @ModelAttribute
    fun addAttributes(model: Model) {
        model.addAttribute("error", "")
        model.addAttribute("first", "")
        model.addAttribute("second", "")
    }

    @RequestMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping(value = ["/calculate"])
    fun doCalculate(
        @RequestParam(value = "first", required = false) first: String,
        @RequestParam(value = "second", required = false) second: String,
        @RequestParam(value = "operation", required = false) operation: String,
        model: Model
    ): String {
        var firstVal: Double
        var secondVal: Double
        var result: Double
        when (operation) {
            "add" ->
                try {
                    firstVal = first.toDouble()
                    secondVal = second.toDouble()
                    result = firstVal + secondVal
                    model.addAttribute("result", String.format("%.2f", result))
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "firstFormatError")
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                }
            "sub" ->
                try {
                    firstVal = first.toDouble()
                    secondVal = second.toDouble()
                    result = firstVal - secondVal
                    model.addAttribute("result", String.format("%.2f", result))
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "secondFormatError")
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                }
            "multiply" ->
                try {
                    firstVal = first.toDouble()
                    secondVal = second.toDouble()
                    result = firstVal * secondVal
                    model.addAttribute("result", String.format("%.2f", result))
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "secondFormatError")
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                }
            "divide" ->
                try {
                    firstVal = first.toDouble()
                    secondVal = second.toDouble()
                    result = firstVal / secondVal
                    model.addAttribute("result", String.format("%.2f", result))
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                } catch (exp: NumberFormatException) {
                    model.addAttribute("error", "secondFormatError")
                    model.addAttribute("first", first)
                    model.addAttribute("second", second)
                }
            else -> {
                model.addAttribute("error", "OperationFormatError")
                model.addAttribute("first", first)
                model.addAttribute("second", second)
            }
        }
        return "home"
    }
}
