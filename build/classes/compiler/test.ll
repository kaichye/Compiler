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
    (OPER 5 Mov [(r 3)]  [(i 1)])
    (OPER 4 Pass []  [(r 3)] [(PARAM_NUM 0)])
    (OPER 7 Mov [(r 4)]  [(i 2)])
    (OPER 6 Pass []  [(r 4)] [(PARAM_NUM 1)])
    (OPER 8 JSR []  [(s func)] [(numParams 2)])
    (OPER 9 Mov [(r 2)]  [(r 1)])
    (OPER 10 Mov [(r 6)]  [(i 5)])
    (OPER 11 LT [(r 5)]  [(r 1)(r 6)])
    (OPER 12 BEQ []  [(r 5)(i 0)(bb 5)])
  )
  (BB 4
    (OPER 13 Mov [(r 8)]  [(i 1)])
    (OPER 14 Add_I [(r 7)]  [(r 1)(r 8)])
    (OPER 15 Mov [(r 7)]  [(r 1)])
    (OPER 16 Mov [(r 10)]  [(i 5)])
    (OPER 17 LT [(r 9)]  [(r 1)(r 10)])
    (OPER 18 BNE []  [(r 9)(i 0)(bb 4)])
  )
  (BB 5
    (OPER 19 Mov [(r 12)]  [(i 1)])
    (OPER 20 EQ [(r 11)]  [(r 1)(r 12)])
    (OPER 21 BEQ []  [(r 11)(i 0)(bb 8)])
  )
  (BB 6
    (OPER 22 Mov [(r 14)]  [(i 2)])
    (OPER 23 EQ [(r 13)]  [(r 1)(r 14)])
    (OPER 24 BEQ []  [(r 13)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 25 Mov [(r 15)]  [(i 11)])
    (OPER 26 Mov [(r 15)]  [(r 1)])
  )
  (BB 10
  )
  (BB 7
    (OPER 47 Mov [(r 25)]  [(i 44)])
    (OPER 48 Mov [(r 25)]  [(r 1)])
  )
  (BB 18
    (OPER 49 Func_Exit []  [])
    (OPER 50 Return []  [(m RetReg)])
  )
  (BB 11
    (OPER 27 Mov [(r 16)]  [(i 5)])
    (OPER 28 Mov [(r 16)]  [(r 1)])
    (OPER 29 Jmp []  [(bb 10)])
  )
  (BB 14
    (OPER 35 Mov [(r 20)]  [(i 4)])
    (OPER 36 Mov [(r 20)]  [(r 1)])
    (OPER 37 Jmp []  [(bb 13)])
  )
  (BB 17
    (OPER 43 Mov [(r 24)]  [(i 9)])
    (OPER 44 Mov [(r 24)]  [(r 1)])
    (OPER 45 Jmp []  [(bb 16)])
  )
  (BB 8
    (OPER 30 Mov [(r 18)]  [(i 3)])
    (OPER 31 EQ [(r 17)]  [(r 1)(r 18)])
    (OPER 32 BEQ []  [(r 17)(i 0)(bb 14)])
  )
  (BB 12
    (OPER 33 Mov [(r 19)]  [(i 12)])
    (OPER 34 Mov [(r 19)]  [(r 1)])
  )
  (BB 13
    (OPER 38 Mov [(r 22)]  [(i 100)])
    (OPER 39 EQ [(r 21)]  [(r 1)(r 22)])
    (OPER 40 BEQ []  [(r 21)(i 0)(bb 17)])
  )
  (BB 15
    (OPER 41 Mov [(r 23)]  [(i 10)])
    (OPER 42 Mov [(r 23)]  [(r 1)])
  )
  (BB 16
    (OPER 46 Jmp []  [(bb 7)])
  )
)
