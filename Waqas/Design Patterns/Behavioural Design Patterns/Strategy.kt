// Strategy --> In Strategy pattern, a class behavior or its algorithm can be changed at run time. This type of design pattern comes under behavior pattern.

// Strategy Interface
interface DiscountStrategy {
    fun applyDiscount(amount: Double): Double
}

//Concrete Strategy
class SeasonalDiscount : DiscountStrategy {
    override fun applyDiscount(amount: Double): Double {
        return amount * 0.50
    }
}
class PromotionalDiscount : DiscountStrategy {
    override fun applyDiscount(amount: Double): Double {
        return amount * 0.7
    }
}

//Context 
class DiscountCalculator(private var strategy: DiscountStrategy) {
    fun setStrategy(strategy: DiscountStrategy) {
        this.strategy = strategy
    }
    fun calculate(amount: Double): Double {
        return strategy.applyDiscount(amount)
    }
}

fun main() {
    val calculator = DiscountCalculator(SeasonalDiscount())
    println("Seasonal discount: ${calculator.calculate(100.0)} %")
    calculator.setStrategy(PromotionalDiscount())
    println("Promotional discount: ${calculator.calculate(100.0)} %")
}
