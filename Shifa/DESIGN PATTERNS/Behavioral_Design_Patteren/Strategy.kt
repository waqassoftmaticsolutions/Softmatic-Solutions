// State Interface
interface SortStrategy {
    fun sort(data: MutableList<Int>)
}

// Concrete Strategies
class BubbleSort : SortStrategy {
    override fun sort(data: MutableList<Int>) {
        println("Sorting using Bubble Sort")
        val n = data.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (data[j] > data[j + 1]) {
                    val temp = data[j]
                    data[j] = data[j + 1]
                    data[j + 1] = temp
                }
            }
        }
    }
}

class QuickSort : SortStrategy {
    override fun sort(data: MutableList<Int>) {
        println("Sorting using Quick Sort")
        quickSort(data, 0, data.size - 1)
    }

    private fun quickSort(data: MutableList<Int>, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(data, low, high)
            quickSort(data, low, pi - 1)
            quickSort(data, pi + 1, high)
        }
    }

    private fun partition(data: MutableList<Int>, low: Int, high: Int): Int {
        val pivot = data[high]
        var i = low - 1
        for (j in low until high) {
            if (data[j] < pivot) {
                i++
                val temp = data[i]
                data[i] = data[j]
                data[j] = temp
            }
        }
        val temp = data[i + 1]
        data[i + 1] = data[high]
        data[high] = temp
        return i + 1
    }
}

class MergeSort : SortStrategy {
    override fun sort(data: MutableList<Int>) {
        println("Sorting using Merge Sort")
        if (data.size > 1) {
            val mid = data.size / 2
            val left = data.subList(0, mid).toMutableList()
            val right = data.subList(mid, data.size).toMutableList()
            sort(left)
            sort(right)
            merge(data, left, right)
        }
    }

    private fun merge(result: MutableList<Int>, left: MutableList<Int>, right: MutableList<Int>) {
        var i = 0
        var j = 0
        var k = 0
        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) {
                result[k] = left[i]
                i++
            } else {
                result[k] = right[j]
                j++
            }
            k++
        }
        while (i < left.size) {
            result[k] = left[i]
            i++
            k++
        }
        while (j < right.size) {
            result[k] = right[j]
            j++
            k++
        }
    }
}

// Context
class SortContext(private var strategy: SortStrategy) {
    fun setStrategy(strategy: SortStrategy) {
        this.strategy = strategy
    }

    fun executeStrategy(data: MutableList<Int>) {
        strategy.sort(data)
    }
}

// Main Function
fun main() {
    val data = mutableListOf(5, 2, 9, 1, 5, 6)

    val context = SortContext(BubbleSort())
    context.executeStrategy(data)
    println("Sorted data: $data")

    data.shuffle()
    context.setStrategy(QuickSort())
    context.executeStrategy(data)
    println("Sorted data: $data")

    data.shuffle()
    context.setStrategy(MergeSort())
    context.executeStrategy(data)
    println("Sorted data: $data")
}
