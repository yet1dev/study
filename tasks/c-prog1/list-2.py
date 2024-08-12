#============================================
#       PROGRAMING 1 COURSE - LIST 2
#============================================
def question_1():
    A=float(input('  A (Integer): '))
    B=float(input('  B (Integer): '))
    print(f'  | Largest number = {max(A,B):.0f}')

#============================================

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
