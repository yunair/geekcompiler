import model.Token

class Parser {
    private val tokenText = StringBuilder()
    private var token: Token = Token(null, "")

    private fun initToken(ch: Char): DFAState {
        token.type?.let {
            println("$it \t\t $tokenText")
        }

        tokenText.clear()
        token.type = null
        return when {
            ch.isLetter() -> {
                if (ch == 'i') {
                    tokenText.append(ch)
                    token.type = TokenType.Int
                    DFAState.ID_INT1
                } else {
                    tokenText.append(ch)
                    token.type = TokenType.Identifier
                    DFAState.ID
                }

            }
            ch.isDigit() -> {
                tokenText.append(ch)
                token.type = TokenType.IntLiteral
                DFAState.IntLiteral
            }
            ch == '=' -> {
                tokenText.append(ch)
                token.type = TokenType.Assignment
                DFAState.Assign
            }
            ch == '=' -> {
                tokenText.append(ch)
                token.type = TokenType.Assignment
                DFAState.Assign
            }
            ch == '+' -> {
                tokenText.append(ch)
                token.type = TokenType.Plus
                DFAState.Plus
            }
            ch == '-' -> {
                tokenText.append(ch)
                token.type = TokenType.Minus
                DFAState.Minus
            }
            ch == '*' -> {
                tokenText.append(ch)
                token.type = TokenType.Multiple
                DFAState.Multiple
            }
            ch == '/' -> {
                tokenText.append(ch)
                token.type = TokenType.Divide
                DFAState.Divide
            }
            ch == '>' -> {
                tokenText.append(ch)
                token.type = TokenType.GT
                DFAState.GT
            }
            else -> {
                DFAState.Initial
            }
        }
    }

    fun transState(ch: Char, state: DFAState): DFAState {
        return when (state) {
            DFAState.ID_INT1 -> {
                if (ch == 'n') {
                    tokenText.append(ch)
                    DFAState.ID_INT2
                } else if (ch.isLetterOrDigit()) {
                    tokenText.append(ch)
                    DFAState.ID
                } else {
                    initToken(ch)
                }
            }
            DFAState.ID_INT2 -> {
                if (ch == 't') {
                    tokenText.append(ch)
                    DFAState.ID_INT3
                } else if (ch.isLetterOrDigit()) {
                    tokenText.append(ch)
                    token.type = TokenType.Identifier
                    DFAState.ID
                } else {
                    initToken(ch)
                }
            }
            DFAState.ID_INT3 -> {
                if (ch.isLetterOrDigit()) {
                    tokenText.append(ch)
                    token.type = TokenType.Identifier
                    DFAState.ID
                } else {
                    token.type = TokenType.Int
                    initToken(ch)
                }
            }
            DFAState.ID -> {
                if (ch.isLetterOrDigit()) {
                    tokenText.append(ch)
                    DFAState.ID
                } else {
                    initToken(ch)
                }
            }
            DFAState.IntLiteral -> {
                if (ch.isDigit()) {
                    tokenText.append(ch)
                    DFAState.IntLiteral
                } else {
                    initToken(ch)
                }
            }
            DFAState.GT -> {
                if (ch == '=') {
                    tokenText.append(ch)
                    DFAState.GE
                } else {
                    initToken(ch)
                }
            }
            DFAState.Assign -> {
                if (ch == '=') {
                    tokenText.append(ch)
                    DFAState.EQ
                } else {
                    initToken(ch)
                }
            }
            DFAState.GE -> {
                initToken(ch)
            }
            DFAState.Plus -> {
                initToken(ch)
            }
            DFAState.Minus -> {
                initToken(ch)
            }
            DFAState.Multiple -> {
                initToken(ch)
            }
            DFAState.Divide -> {
                initToken(ch)
            }
            else -> {
                initToken(ch)
            }
        }
    }
}