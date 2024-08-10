#============================================
#       PROGRAMING 1 COURSE - LIST 1
#============================================
print('\nQuestion 1')

pi=3.14
radius=float(input('  Radius: '))
print(f'  | Circle Area = {pi*radius**2}')

#============================================
print('\nQuestion 2')

earning=float(input('  Earning/hour: '))
hour=int(input('  Hours/month: '))
print(f'  | Earning/month = {hour*earning}')

#============================================
print('\nQuestion 3')

F=float(input('  Farenheit: '))
print(f'  | Celcius = {5*(F-32)/9}')

#============================================
print('\nQuestion 4')

C=float(input('  Celcius: '))
print(f'  | Farenheit = {(9*C/5)+32}')

#============================================
print('\nQuestion 5')
A=int(input('  A (Integer): '))
B=int(input('  B (Integer): '))
C=float(input('  C (Real): '))

print(f'  | 2A * ½B = {(2*A)*(B/2)}')
print(f'  | 3A + C = {3*A+C}')
print(f'  | C³ = {C**3}')

#============================================
print('\nQuestion 6')
weight=int(input('  Fish weight: '))
excess=(weight>50)*(weight-50)

print(f'  | Excess = {excess}')
print(f'  | Fine = {4*excess}')
