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
