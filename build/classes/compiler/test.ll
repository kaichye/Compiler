(DATA  a)
(DATA  b)
(FUNCTION  func  [(int c) (int d)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 6)]  [(i 123)])
    (OPER 5 Add_I [(r 5)]  [(r 6)(r 1)])
    (OPER 6 Mov [(r 5)]  [(r 3)])
    (OPER 7 Mov [(m RetReg)]  [(r 3)])
  )
  (BB 4
    (OPER 8 Func_Exit []  [])
    (OPER 9 Return []  [(m RetReg)])
  )
)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Load [(r 2)]  [(s a)(i 0)])
    (OPER 5 Mov [(r 2)]  [(r 1)])
    (OPER 7 Mov [(r 4)]  [(i 1)])
    (OPER 6 Pass []  [(r 4)] [(PARAM_NUM 0)])
    (OPER 9 Mov [(r 5)]  [(i 2)])
    (OPER 8 Pass []  [(r 5)] [(PARAM_NUM 1)])
    (OPER 10 JSR []  [(s func)] [(numParams 2)])
    (OPER 11 Mov [(r 3)]  [(r 1)])
  )
  (BB 4
    (OPER 12 Func_Exit []  [])
    (OPER 13 Return []  [(m RetReg)])
  )
)
