/* 
DRIVE FOR DISTANCE

driveForwardvalue / distance driven = difference factor
difference factor * distance driven inches = driveForwardvalue 

5000 / 45in = 111.1
111.1 * 45in = 5000

2500 / 111.1 = 22.5    
    ?x = 22.5
    x1 = 25.5
    x2 = 23.5
    x3 = 23 even
**************
          
3000 / 111.1 = 27
    ?x = 27
    x1 = 26 even
    x2 = 26.75
    x3 = 27
**************

4000 / 111.1 = 36
    ?x = 36
    x1 = 33.5
    x2 = 34.5
    x3 = 33
**************


AUTOTURN

y = start
x = turn
y +/- x = ~90 (hopefully)
**************
y1 = -162
x1 = -285
-162 - 90 = -252
-252 /= 285
overshooting = -33
91%
**************
y2 = -25
x2 = -141
-25 - 90 = -115
-115 /= -141
overshooting = -26
94%
**************
y3 = -140
x3 = -257
-140 - 90 = -230
-230 /= -257
overshooting: -27
90%
**************
-27 + -26 + -33 = -86
-86 / 3 = -28.6
average percent: 91

LOWERED SPEED RATE
y1 = -85
x1 = -186
-85 - 90 = -175
-175 /= -186
overshooting = -11

speed = turbo*(1 - (stopitnow - getyaw) / (stopitnow))
if getyaw is within stoprange then adjust
goal - stopangle <= getyaw <= goal+stopangle
Math.abs(getYaw() - goal) < slowangle) 

Math.abs(getYaw() - goal) < stopangle) 

*/
