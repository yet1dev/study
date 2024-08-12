#============================================
#       PROGRAMING 1 COURSE - LIST 1
#============================================
print('\nQuestion 1 - Circle Area')

pi=3.14
radius=float(input('  Radius: '))
print(f'  | Circle Area = {pi*radius**2:.2f}')

#============================================
print('\nQuestion 2 - Salary of Month')

earning=float(input('  Earning/hour: '))
hour=int(input('  Hours/month: '))
print(f'  | Earning/month = R$ {hour*earning:.2f}')

#============================================
print('\nQuestion 3 - Farenheit to Celcius')

F=float(input('  Farenheit: '))
print(f'  | Celcius = {5*(F-32)/9:.1f} C')

#============================================
print('\nQuestion 4 - Celcius to Farenheit')

C=float(input('  Celcius: '))
print(f'  | Farenheit = {(9*C/5)+32:.1f} F')

#============================================
print('\nQuestion 5 - Calculator')
A=int(input('  A (Integer): '))
B=int(input('  B (Integer): '))
C=float(input('  C (Real): '))

print(f'  |a| 2A * ½B = {(2*A)*(B/2):.2f}')
print(f'  |b| 3A + C = {3*A+C:.2f}')
print(f'  |c| C³ = {C**3:.2f}')

#============================================
print('\nQuestion 6 - Fishery')
weight=float(input('  Fish weight: '))
excess=(weight>50)*(weight-50)

print(f'  | Excess = {excess:.1f} kg')
print(f'  | Fine = R$ {4*excess:.2f}')

#============================================
print('\nQuestion 7 - Cans and Gallons')

area=float(input('  Paint area: '))

c = (area//108) + (area%108>0)    # can units
g = (area//21.5) + (area%21.5>0)  # gallon units

mc, mt = area//108, area%108 # mixed cans and todo
mg = mt//21.5 + (mt%21.5>0)  # mixed gallons

mc = mc + (mg>3)   # mixed cans
mg = mg * (mg<=3)  # mixed gallons

mp = mc*80 + mg*25  # mixed price
cp, gp = c*80, g*25 # can and gallon prices

print(f'  |a| Cans = {c:.0f} cans for R$ {cp:.2f}')
print(f'  |b| Gallons = {g:.0f} gallons for R$ {gp:.2f}')
print(f'  |c| Cheapest = {mc:.0f} cans and {mg:.0f} gallons for R$ {mp:.2f}')

#============================================
print('\nQuestion 8 - Salary Calculator')

earning=float(input('  Earning/hour: '))
hour=int(input('  Hours/month: '))
gloss=hour*earning

print(f'  |a| Gloss pay = R$ {gloss:.2f}')
print(f'  |b| INSS tax = R$ {gloss*0.08:.2f}')
print(f'  |c| Union fee = R$ {gloss*0.05:.2f}')
print(f'  |d| Net pay = R$ {gloss*0.76:.2f}')
print(f'  |e| Income tax = R$ {gloss*0.11:.2f}')

#============================================
print('\nQuestion 9 - Salary Incriase')

pay=float(input('  Salary: '))
incr=( (pay<=280)+(pay<=700)+(pay<=1500)+1 )*0.05

print(f'  |a| Salary = R$ {pay:.2f}')
print(f'  |b| Incriase = {incr*100:.0f}%')
print(f'  |c| Incriase Value = R$ {pay*incr:.2f}')
print(f'  |d| Total salary = R$ {pay+pay*incr:.2f}')

#============================================
print('\nQuestion 10 - Triangle Test')

A,B,C= sorted([
    float(input('  Edge A: ')),
    float(input('  Edge B: ')),
    float(input('  Edge C: '))
])

triangle = (A!=B!=C) * 'Is a Scalene Triangle'
triangle = (A==B==C) * 'Is a Equilateral Triangle'
triangle = ((A==B) ^ (B==C)) * 'Is a Isosceles Triangle'
triangle = (A+B>C) * triangle + (A+B<=C) * 'Not is a Triangle'

print(f'  | {triangle}')
