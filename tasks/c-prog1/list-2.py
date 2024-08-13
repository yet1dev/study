#============================================
#       PROGRAMING 1 COURSE - LIST 2
#============================================
def question_1():
    A=int(input('  A (Integer): '))
    B=int(input('  B (Integer): '))
    print(f'  | Largest number = {max(A,B):.0f}')

#============================================
def question_2():
    A=int(input('  A (Integer): '))
    print('  | A is ' + [ "Negative","Positive","Neutral" ][ (A>=0)+(A==0) ])

#============================================
def question_3():
    C=input('  Sex: ')
    OUT=[ 'Invalid','Masculine','Feminine' ][ (C=='M') + (C=='F')*2 ]
    print(f'  | This is a {OUT} sex')

#============================================
def question_4():
    year=input('  Year: ')
    OUT=[ 'Not is a leap','Is a leap' ][int(year)%4==0]
    print('  | ' + ["Invalid",OUT][len(year)==4] + ' Year')

#============================================
def question_5():
    char=input('  Put a char: ')
    print('  | Is a ' + [ 'consoant','vowel' ][char in 'AEIOUaeiou'] )

#============================================
def question_6():
    A=int(input('  A (Integer): '))
    B=int(input('  B (Integer): '))
    C=int(input('  C (Integer): '))

    print('  | Descending List = ', list(reversed(sorted([A,B,C]))) )

#============================================
def question_7():
    T1=float(input('  Test 1: '))
    T2=float(input('  Test 2: '))
    med = (T1+T2)/2
    VAL = [ 'rejected','approved' ][med>=7] + (med==10)*' with distinction'

    print(f'  | Median = {med:.2f}, You are {VAL}')

#============================================
def question_8():
    A=int(input('  A (Integer): '))
    B=int(input('  B (Integer): '))
    C=int(input('  C (Integer): '))
    print(f'  | Largest = {max(A,B,C)}')

#============================================
def question_9():
    A=int(input('  A (Integer): '))
    B=int(input('  B (Integer): '))
    C=int(input('  C (Integer): '))
    print(f'  | Smallest = {min(A,B,C)}')
    print(f'  | Largest = {max(A,B,C)}')

#============================================
def question_10():
    A=float(input('  1° Product Price: '))
    B=float(input('  2° Product Price: '))
    C=float(input('  3° Product Price: '))

    if (min(A,B,C)==A): cheaper = 1
    if (min(A,B,C)==B): cheaper = 2
    if (min(A,B,C)==C): cheaper = 3

    print(f'  | Cheaper = {cheaper}° Product for R$ {min(A,B,C):.2f}')

#============================================
def question_11():
    C=input('  Study shift: ')
    i = (C=='M') + (C=='A')*2 + (C=='N')*3
    shift = ['Invalid Value', 'Morning', 'Afternoon', 'Night']
    print(f'  | {(i!=0)*"Good"} {shift[i]}')

#============================================
def question_12():
    pay=float(input('  Salary: '))
    incr=( (pay<=280)+(pay<=700)+(pay<=1500)+1 )*0.05

    print(f'  |a| Salary = R$ {pay:.2f}')
    print(f'  |b| Incriase = {incr*100:.0f}%')
    print(f'  |c| Incriase Value = R$ {pay*incr:.2f}')
    print(f'  |d| Total salary = R$ {pay+pay*incr:.2f}')

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
