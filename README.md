RG-RPG
======

RG-RPG is a Java-based text, role-playing-gal-game, in which you have to carry many girls. The RG-RPG acronym is a recursive one,
and it means "RG-RPG is a Gal Role playing game Pointing on Girls."

It is developed entirely with emacs(-nox) and it includes a Makefile which uses plain old javac executable (POJACE). Its main
reason to be is my fun and a growing will to learn emacs from its basics, using it instead of only reading a book.

I will try to develop this game writing with all the patterns and best practices I've learnt during years of work and study. I hope
you want to play with it when it is completed, and I hope you will file me any issue or improvement you find out during this game.


INSTALLING
==========

To install RG-RPG please make sure you can run the following software:

1. java (jre or jdk) >= 17 :: It is better to have OpenJDK instead of a proprietary one, I develop with O-JDK, so it will be 100%
   compatible with it, I cannot guarantee for any other JRE or JDK you want to run.

2. maven >= 3.8.0 :: It is needed in order to do dependency management properly.

3. make >= 3.82 :: It is needed in order to launch 'make && sudo make install'

How-To Install RG-RPG
---------------------

In the KISS philosophy I tried to 'Keep It Simple, Stupid' even the installation part. So I decided to use a cross-platform environment
as Java and the well-known in Unix systems program Make. To install this software you have to run just the following command:

$ make

\# make install

Please note that $ means own user's shell, besides # means root shell.


COPYING
=======

Copyright (C) 2021 Alessandro 'kLeZ' Accardo

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You have received a copy of the GNU General Public License in a file called "gpl.txt" along with this program. Anyway please, see
<http://www.gnu.org/licenses/> for more details.
