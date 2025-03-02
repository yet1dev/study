#============================================
#     PROGRAMING 1 COURSE - LIST 3 LOOPS
#============================================
def question_1():
    N=float(input('  Test Score: '))
    while True:
        if (0<=N<=10): break
        N=float(input('  Not Score, try again: '))

#============================================
def question_2():
    while True:
        name=input('  Name: ')
        secret=input('  Password: ')
        if (name != secret): break
        print('  | Password cant be your name\n')

#============================================
def question_3():
    name,age,pay,sex,state = 'k',-1,0,'k','k'
    
    while len(name)<=3:        name=input('  Name: ')
    while not (0<=age<=150):   age=int(input('  Age: '))
    while pay<=0:              pay=float(input('  Salary: '))
    while not sex in 'mf':     sex=input('  Sex: ') 
    while not state in 'smwd': state=input('  State: ') 
    # STATE: single, married, widowed, divocied

#============================================
def question_4():
    N,A,B = 0, 80_000, 200_000
    while (A<B): A,B,N = 1.03*A, 1.015*B, N+1

    print(f'  | In {N} years')
    print(f'  | Country A have {A:.0f} peoples')
    print(f'  | Country B have {B:.0f} peoples')

#============================================
def question_5():
    N = 0
    A = int(input('  Population of Country A: '))
    B = int(input('  Population of Country B: '))
    IA = float(input('  Incriase rate % of Country A: '))
    IB = float(input('  Incriase rate % of Country B: '))
    IA, IB = IA/100 + 1, IB/100 + 1

    while (A<B): A,B,N = IA*A, IB*B, N+1

    print(f'  | In {N} years')
    print(f'  | Country A have {A:.0f} peoples')
    print(f'  | Country B have {B:.0f} peoples')

#============================================
def question_6():
    print('  for:', end='')
    for x in range(1,50,2): print(f' {x}', end='')

    print('\n  while:', end=''); x=1
    while x<50: print(f' {x}', end=''); x+=2
    print()

#============================================
def question_7():
    n = int(input('  Number 1 to 10: '))
    print(f'  | FOR Multiplication Table of {n}: ')
    for x in range(1,11): print(f'    | {n} x {x} = {n*x}')
    
    print(f'\n  | WHILE Multiplication Table of {n}: '); x=0
    while x<11: print(f'    | {n} x {x} = {n*x}'); x+=1

#============================================
def question_8():
    A = float(input('  A (Real): '))
    B = int(input('  B (Integer): '))

    N=A
    for x in range(1,B): N*=N
    print(f'  | FOR   {A:.2f} ** {B} = {N:.2f}')

    N,x = A, 1
    while x<B: N*=N; x+=1
    print(f'  | WHILE {A:.2f} ** {B} = {N:.2f}')

#============================================
def question_9():
    odd,even = 0,0
    for x in range(1,11):
        N=int(input(f'  {x}° Number: '))
        odd+= (N%2==1); even+= (N%2==0)
    print(f'  | FOR Odd Numbers = {odd}')
    print(f'  | FOR Even Numbers = {even}\n')

    odd,even,x = 0,0,0
    while x<10:
        x+=1
        N=int(input(f'  {x}° Number: '))
        odd+= (N%2==1); even+= (N%2==0)
    print(f'  | WHILE Odd Numbers = {odd}')
    print(f'  | WHILE Even Numbers = {even}')

#============================================
def question_10():
    A,B = 1,1
    n = int(input(f'  N-fib: '))

    print('  | FOR =   1', end='')
    for x in range(1,n): print(f' {B}', end=''); B+=A; A=B-A

    print('\n  | WHILE = 1', end=''); A,B,x = 1,1,1
    while x<n: x+=1; print(f' {B}', end=''); B+=A; A=B-A
    print('')

#============================================
def question_11():
    r=1
    n = int(input(f'  N-fat: '))
    for x in range(1,n+1): r*=x
    print(f'  | FOR = {r}'); r=1
    
    while n>1: r*=n; n-=1
    print(f'  | WHILE = {r}')

#============================================
def question_12():
    N = int(input(f'  Prime test: '))

    isprime=''
    for x in range(2,N):
        if N%x == 0: isprime='not ' 
    print(f'  | FOR = A {isprime}is a prime')

    isprime=''; x=N
    while x>2:
        x-=1
        if N%x == 0: isprime='not '
    print(f'  | WHILE = A {isprime}is a prime')

#============================================
def question_13():
    N = int(input(f'  Number of Scores: '))
    total=0

    for x in range(1,N+1):
        total += float(input(f'  {x}° Test Score: '))
    print(f'  | Median of Scores = {total/N:.2f}')

#============================================
#       INTERACTION WITH THE QUESTIONS
#============================================
while True:
    match int(input('\nQuestion: ')):
        case 0:  break
        case 1:  question_1()
        case 2:  question_2()
        case 3:  question_3()
        case 4:  question_4()
        case 5:  question_5()
        case 6:  question_6()
        case 7:  question_7()
        case 8:  question_8()
        case 9:  question_9()
        case 10: question_10()
        case 11: question_11()
        case 12: question_12()
        case 13: question_13()
