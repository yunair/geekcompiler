enum class DFAState {
    Initial, ID, IntLiteral, GT, GE, EQ,
    Assign, Plus, Minus, Multiple, Divide,
    ID_INT1, ID_INT2, ID_INT3
}