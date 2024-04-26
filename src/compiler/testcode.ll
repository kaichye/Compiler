(DATA  a)
(FUNCTION  addThem  [(int d) (int e)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 4)]  [(r 1)(r 2)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(m RetReg)]  [(r 3)])
  )
  (BB 4
    (OPER 7 Func_Exit []  [])
    (OPER 8 Return []  [(m RetReg)])
  )
)
(FUNCTION  putDigit  [(int s)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 5 Mov [(r 4)]  [(i 48)])
    (OPER 6 Add_I [(r 3)]  [(r 4)(r 1)])
    (OPER 4 Pass []  [(r 3)] [(PARAM_NUM 0)])
    (OPER 7 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 8 Mov [(r 2)]  [(m RetReg)])
  )
  (BB 4
    (OPER 9 Func_Exit []  [])
    (OPER 10 Return []  [(m RetReg)])
  )
)
(FUNCTION  printInt  [(int r)]
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 4)]  [(i 0)])
    (OPER 5 Mov [(r 3)]  [(r 4)])
    (OPER 6 Mov [(r 6)]  [(i 10000)])
    (OPER 7 GTE [(r 5)]  [(r 1)(r 6)])
    (OPER 8 BEQ []  [(r 5)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 10 Mov [(r 8)]  [(i 45)])
    (OPER 9 Pass []  [(r 8)] [(PARAM_NUM 0)])
    (OPER 11 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 12 Mov [(r 7)]  [(m RetReg)])
    (OPER 14 Mov [(r 10)]  [(i 1)])
    (OPER 13 Pass []  [(r 10)] [(PARAM_NUM 0)])
    (OPER 15 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 16 Mov [(r 9)]  [(m RetReg)])
  )
  (BB 5
  )
  (BB 19
    (OPER 80 Func_Exit []  [])
    (OPER 81 Return []  [(m RetReg)])
  )
  (BB 11
    (OPER 47 Mov [(r 30)]  [(i 1)])
    (OPER 48 EQ [(r 29)]  [(r 3)(r 30)])
    (OPER 49 BEQ []  [(r 29)(i 0)(bb 13)])
  )
  (BB 12
    (OPER 51 Mov [(r 32)]  [(i 0)])
    (OPER 50 Pass []  [(r 32)] [(PARAM_NUM 0)])
    (OPER 52 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 53 Mov [(r 31)]  [(m RetReg)])
  )
  (BB 13
    (OPER 54 Jmp []  [(bb 10)])
  )
  (BB 16
    (OPER 68 Mov [(r 42)]  [(i 1)])
    (OPER 69 EQ [(r 41)]  [(r 3)(r 42)])
    (OPER 70 BEQ []  [(r 41)(i 0)(bb 18)])
  )
  (BB 17
    (OPER 72 Mov [(r 44)]  [(i 0)])
    (OPER 71 Pass []  [(r 44)] [(PARAM_NUM 0)])
    (OPER 73 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 74 Mov [(r 43)]  [(m RetReg)])
  )
  (BB 18
    (OPER 75 Jmp []  [(bb 15)])
  )
  (BB 6
    (OPER 17 Mov [(r 12)]  [(i 1000)])
    (OPER 18 GTE [(r 11)]  [(r 1)(r 12)])
    (OPER 19 BEQ []  [(r 11)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 20 Mov [(r 14)]  [(i 1000)])
    (OPER 21 Div_I [(r 13)]  [(r 1)(r 14)])
    (OPER 22 Mov [(r 2)]  [(r 13)])
    (OPER 23 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 24 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 25 Mov [(r 15)]  [(m RetReg)])
    (OPER 26 Mov [(r 18)]  [(i 1000)])
    (OPER 27 Mul_I [(r 17)]  [(r 2)(r 18)])
    (OPER 28 Sub_I [(r 16)]  [(r 1)(r 17)])
    (OPER 29 Mov [(r 1)]  [(r 16)])
    (OPER 30 Mov [(r 19)]  [(i 1)])
    (OPER 31 Mov [(r 3)]  [(r 19)])
  )
  (BB 8
    (OPER 32 Mov [(r 21)]  [(i 100)])
    (OPER 33 GTE [(r 20)]  [(r 1)(r 21)])
    (OPER 34 BEQ []  [(r 20)(i 0)(bb 11)])
  )
  (BB 9
    (OPER 35 Mov [(r 23)]  [(i 100)])
    (OPER 36 Div_I [(r 22)]  [(r 1)(r 23)])
    (OPER 37 Mov [(r 2)]  [(r 22)])
    (OPER 38 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 39 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 40 Mov [(r 24)]  [(m RetReg)])
    (OPER 41 Mov [(r 27)]  [(i 100)])
    (OPER 42 Mul_I [(r 26)]  [(r 2)(r 27)])
    (OPER 43 Sub_I [(r 25)]  [(r 1)(r 26)])
    (OPER 44 Mov [(r 1)]  [(r 25)])
    (OPER 45 Mov [(r 28)]  [(i 1)])
    (OPER 46 Mov [(r 3)]  [(r 28)])
  )
  (BB 10
    (OPER 55 Mov [(r 34)]  [(i 10)])
    (OPER 56 GTE [(r 33)]  [(r 1)(r 34)])
    (OPER 57 BEQ []  [(r 33)(i 0)(bb 16)])
  )
  (BB 14
    (OPER 58 Mov [(r 36)]  [(i 10)])
    (OPER 59 Div_I [(r 35)]  [(r 1)(r 36)])
    (OPER 60 Mov [(r 2)]  [(r 35)])
    (OPER 61 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 62 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 63 Mov [(r 37)]  [(m RetReg)])
    (OPER 64 Mov [(r 40)]  [(i 10)])
    (OPER 65 Mul_I [(r 39)]  [(r 2)(r 40)])
    (OPER 66 Sub_I [(r 38)]  [(r 1)(r 39)])
    (OPER 67 Mov [(r 1)]  [(r 38)])
  )
  (BB 15
    (OPER 76 Pass []  [(r 1)] [(PARAM_NUM 0)])
    (OPER 77 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 78 Mov [(r 45)]  [(m RetReg)])
    (OPER 79 Jmp []  [(bb 5)])
  )
)
(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Mov [(r 6)]  [(i 5)])
    (OPER 5 Mov [(r 2)]  [(r 6)])
    (OPER 6 Mov [(r 1)]  [(r 2)])
    (OPER 7 Mov [(r 8)]  [(i 5)])
    (OPER 8 EQ [(r 7)]  [(r 1)(r 8)])
    (OPER 9 BEQ []  [(r 7)(i 0)(bb 6)])
  )
  (BB 4
    (OPER 10 Load [(r 9)]  [(s a)(i 0)])
    (OPER 11 Mov [(r 10)]  [(i 3)])
    (OPER 12 Store []  [(r 10)(s a)])
  )
  (BB 5
    (OPER 17 Mov [(r 13)]  [(i 0)])
    (OPER 18 Mov [(r 3)]  [(r 13)])
    (OPER 19 Mov [(r 14)]  [(i 1)])
    (OPER 20 Mov [(r 5)]  [(r 14)])
    (OPER 21 Mov [(r 16)]  [(i 8)])
    (OPER 22 LTE [(r 15)]  [(r 5)(r 16)])
    (OPER 23 BEQ []  [(r 15)(i 0)(bb 8)])
  )
  (BB 7
    (OPER 24 Add_I [(r 17)]  [(r 3)(r 5)])
    (OPER 25 Mov [(r 3)]  [(r 17)])
    (OPER 26 Mov [(r 19)]  [(i 1)])
    (OPER 27 Add_I [(r 18)]  [(r 5)(r 19)])
    (OPER 28 Mov [(r 5)]  [(r 18)])
    (OPER 29 Mov [(r 21)]  [(i 8)])
    (OPER 30 LTE [(r 20)]  [(r 5)(r 21)])
    (OPER 31 BNE []  [(r 20)(i 0)(bb 7)])
  )
  (BB 8
    (OPER 32 Mov [(r 23)]  [(i 3)])
    (OPER 33 Div_I [(r 22)]  [(r 3)(r 23)])
    (OPER 34 Mov [(r 4)]  [(r 22)])
    (OPER 35 Mov [(r 25)]  [(i 4)])
    (OPER 36 Mul_I [(r 24)]  [(r 4)(r 25)])
    (OPER 37 Mov [(r 3)]  [(r 24)])
    (OPER 39 Load [(r 27)]  [(s a)(i 0)])
    (OPER 38 Pass []  [(r 27)] [(PARAM_NUM 0)])
    (OPER 40 Pass []  [(r 1)] [(PARAM_NUM 1)])
    (OPER 41 JSR []  [(s addThem)] [(numParams 2)])
    (OPER 42 Mov [(r 26)]  [(m RetReg)])
    (OPER 43 Mov [(r 2)]  [(r 26)])
    (OPER 45 Mov [(r 29)]  [(i 56)])
    (OPER 44 Pass []  [(r 29)] [(PARAM_NUM 0)])
    (OPER 46 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 47 Mov [(r 28)]  [(m RetReg)])
    (OPER 49 Mov [(r 31)]  [(i 61)])
    (OPER 48 Pass []  [(r 31)] [(PARAM_NUM 0)])
    (OPER 50 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 51 Mov [(r 30)]  [(m RetReg)])
    (OPER 53 Add_I [(r 33)]  [(r 2)(r 3)])
    (OPER 52 Pass []  [(r 33)] [(PARAM_NUM 0)])
    (OPER 54 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 55 Mov [(r 32)]  [(m RetReg)])
    (OPER 57 Mov [(r 35)]  [(i 10)])
    (OPER 56 Pass []  [(r 35)] [(PARAM_NUM 0)])
    (OPER 58 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 59 Mov [(r 34)]  [(m RetReg)])
    (OPER 60 Mov [(r 36)]  [(i 0)])
    (OPER 61 Mov [(r 5)]  [(r 36)])
    (OPER 62 Mov [(r 38)]  [(i 10)])
    (OPER 63 LT [(r 37)]  [(r 5)(r 38)])
    (OPER 64 BEQ []  [(r 37)(i 0)(bb 10)])
  )
  (BB 9
    (OPER 66 Mov [(r 41)]  [(i 48)])
    (OPER 67 Add_I [(r 40)]  [(r 41)(r 5)])
    (OPER 65 Pass []  [(r 40)] [(PARAM_NUM 0)])
    (OPER 68 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 69 Mov [(r 39)]  [(m RetReg)])
    (OPER 70 Mov [(r 43)]  [(i 1)])
    (OPER 71 Add_I [(r 42)]  [(r 5)(r 43)])
    (OPER 72 Mov [(r 5)]  [(r 42)])
    (OPER 73 Mov [(r 45)]  [(i 10)])
    (OPER 74 LT [(r 44)]  [(r 5)(r 45)])
    (OPER 75 BNE []  [(r 44)(i 0)(bb 9)])
  )
  (BB 10
    (OPER 77 Mov [(r 47)]  [(i 10)])
    (OPER 76 Pass []  [(r 47)] [(PARAM_NUM 0)])
    (OPER 78 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 79 Mov [(r 46)]  [(m RetReg)])
    (OPER 81 Mov [(r 49)]  [(i 67)])
    (OPER 80 Pass []  [(r 49)] [(PARAM_NUM 0)])
    (OPER 82 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 83 Mov [(r 48)]  [(m RetReg)])
    (OPER 85 Mov [(r 51)]  [(i 83)])
    (OPER 84 Pass []  [(r 51)] [(PARAM_NUM 0)])
    (OPER 86 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 87 Mov [(r 50)]  [(m RetReg)])
    (OPER 89 Mov [(r 53)]  [(i 3510)])
    (OPER 88 Pass []  [(r 53)] [(PARAM_NUM 0)])
    (OPER 90 JSR []  [(s printInt)] [(numParams 1)])
    (OPER 91 Mov [(r 52)]  [(m RetReg)])
    (OPER 93 Mov [(r 55)]  [(i 10)])
    (OPER 92 Pass []  [(r 55)] [(PARAM_NUM 0)])
    (OPER 94 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 95 Mov [(r 54)]  [(m RetReg)])
    (OPER 96 Mov [(r 56)]  [(i 0)])
    (OPER 97 Mov [(r 1)]  [(r 56)])
    (OPER 98 Mov [(r 57)]  [(i 1)])
    (OPER 99 Mov [(r 2)]  [(r 57)])
    (OPER 100 Mov [(r 58)]  [(i 1)])
    (OPER 101 Mov [(r 3)]  [(r 58)])
    (OPER 102 Mov [(r 59)]  [(i 0)])
    (OPER 103 Mov [(r 4)]  [(r 59)])
    (OPER 104 Mov [(r 60)]  [(i 0)])
    (OPER 105 Mov [(r 5)]  [(r 60)])
    (OPER 106 Mov [(r 62)]  [(i 0)])
    (OPER 107 EQ [(r 61)]  [(r 1)(r 62)])
    (OPER 108 BEQ []  [(r 61)(i 0)(bb 13)])
  )
  (BB 11
    (OPER 109 Mov [(r 64)]  [(i 0)])
    (OPER 110 EQ [(r 63)]  [(r 2)(r 64)])
    (OPER 111 BEQ []  [(r 63)(i 0)(bb 16)])
  )
  (BB 14
    (OPER 112 Mov [(r 65)]  [(i 1)])
    (OPER 113 Mov [(r 5)]  [(r 65)])
  )
  (BB 15
  )
  (BB 12
    (OPER 132 Mov [(r 75)]  [(i 10)])
    (OPER 133 EQ [(r 74)]  [(r 5)(r 75)])
    (OPER 134 BEQ []  [(r 74)(i 0)(bb 25)])
  )
  (BB 23
    (OPER 136 Mov [(r 77)]  [(i 99)])
    (OPER 135 Pass []  [(r 77)] [(PARAM_NUM 0)])
    (OPER 137 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 138 Mov [(r 76)]  [(m RetReg)])
    (OPER 140 Mov [(r 79)]  [(i 0)])
    (OPER 139 Pass []  [(r 79)] [(PARAM_NUM 0)])
    (OPER 141 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 142 Mov [(r 78)]  [(m RetReg)])
    (OPER 144 Mov [(r 81)]  [(i 0)])
    (OPER 143 Pass []  [(r 81)] [(PARAM_NUM 0)])
    (OPER 145 JSR []  [(s putDigit)] [(numParams 1)])
    (OPER 146 Mov [(r 80)]  [(m RetReg)])
    (OPER 148 Mov [(r 83)]  [(i 108)])
    (OPER 147 Pass []  [(r 83)] [(PARAM_NUM 0)])
    (OPER 149 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 150 Mov [(r 82)]  [(m RetReg)])
  )
  (BB 24
    (OPER 172 Mov [(r 94)]  [(i 10)])
    (OPER 171 Pass []  [(r 94)] [(PARAM_NUM 0)])
    (OPER 173 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 174 Mov [(r 93)]  [(m RetReg)])
    (OPER 176 Mov [(r 95)]  [(i 0)])
    (OPER 175 Mov [(m RetReg)]  [(r 95)])
  )
  (BB 26
    (OPER 177 Func_Exit []  [])
    (OPER 178 Return []  [(m RetReg)])
  )
  (BB 6
    (OPER 13 Load [(r 11)]  [(s a)(i 0)])
    (OPER 14 Mov [(r 12)]  [(i 4)])
    (OPER 15 Store []  [(r 12)(s a)])
    (OPER 16 Jmp []  [(bb 5)])
  )
  (BB 22
    (OPER 124 Mov [(r 72)]  [(i 3)])
    (OPER 125 Mov [(r 5)]  [(r 72)])
    (OPER 126 Jmp []  [(bb 21)])
  )
  (BB 19
    (OPER 119 Mov [(r 70)]  [(i 0)])
    (OPER 120 EQ [(r 69)]  [(r 4)(r 70)])
    (OPER 121 BEQ []  [(r 69)(i 0)(bb 22)])
  )
  (BB 20
    (OPER 122 Mov [(r 71)]  [(i 10)])
    (OPER 123 Mov [(r 5)]  [(r 71)])
  )
  (BB 21
    (OPER 127 Jmp []  [(bb 18)])
  )
  (BB 16
    (OPER 114 Mov [(r 67)]  [(i 0)])
    (OPER 115 EQ [(r 66)]  [(r 3)(r 67)])
    (OPER 116 BEQ []  [(r 66)(i 0)(bb 19)])
  )
  (BB 17
    (OPER 117 Mov [(r 68)]  [(i 2)])
    (OPER 118 Mov [(r 5)]  [(r 68)])
  )
  (BB 18
    (OPER 128 Jmp []  [(bb 15)])
  )
  (BB 13
    (OPER 129 Mov [(r 73)]  [(i 0)])
    (OPER 130 Mov [(r 5)]  [(r 73)])
    (OPER 131 Jmp []  [(bb 12)])
  )
  (BB 25
    (OPER 152 Mov [(r 85)]  [(i 98)])
    (OPER 151 Pass []  [(r 85)] [(PARAM_NUM 0)])
    (OPER 153 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 154 Mov [(r 84)]  [(m RetReg)])
    (OPER 156 Mov [(r 87)]  [(i 97)])
    (OPER 155 Pass []  [(r 87)] [(PARAM_NUM 0)])
    (OPER 157 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 158 Mov [(r 86)]  [(m RetReg)])
    (OPER 160 Mov [(r 89)]  [(i 100)])
    (OPER 159 Pass []  [(r 89)] [(PARAM_NUM 0)])
    (OPER 161 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 162 Mov [(r 88)]  [(m RetReg)])
    (OPER 164 Mov [(r 91)]  [(i 61)])
    (OPER 163 Pass []  [(r 91)] [(PARAM_NUM 0)])
    (OPER 165 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 166 Mov [(r 90)]  [(m RetReg)])
    (OPER 167 Pass []  [(r 5)] [(PARAM_NUM 0)])
    (OPER 168 JSR []  [(s printInt)] [(numParams 1)])
    (OPER 169 Mov [(r 92)]  [(m RetReg)])
    (OPER 170 Jmp []  [(bb 24)])
  )
)
