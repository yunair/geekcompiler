fun main() {
    val parser = Parser()
    var state = DFAState.Initial
//    val args = "age >= 45 "
//    val args = "intA = 45 "
    val args = "2+3*5 "
    for (arg in args) {
        state = parser.transState(arg, state)
    }
}