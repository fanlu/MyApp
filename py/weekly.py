#!/usr/bin/env python
from BeautifulSoup import BeautifulSoup
from urllib import urlopen


def main():
    soup = BeautifulSoup(urlopen('http://www.weeklysh.com/News/TypeShow.aspx?KeyWord=%E5%A5%B3%E5%B7%AB%E5%BA%97'))
    links = soup('ul')
    print links[0]
    print '1111111111111'

if __name__ == '__main__':
    main()
