#!/usr/bin/env python
#coding=utf-8
from BeautifulSoup import BeautifulSoup
from urllib import urlopen
import re


def main():
    soup = BeautifulSoup(urlopen('http://www.weeklysh.com/News/TypeShow.aspx?KeyWord=%E5%A5%B3%E5%B7%AB%E5%BA%97'))
    #links = soup('ul')
    #print soup.html.head.title
    #print soup.html.head.title.name
    #print soup.title
    a = soup.findAll(id=re.compile("ctl00_MainPlaceHolder_TypeShow1_NewsList1_RP_Current"))
    deal('http://www.weeklysh.com/News/ArticleShow.aspx?ArticleID=12349')
    for li in a:
        href = 'http://www.weeklysh.com/News' + li['href']
        #dealdetial(href)


def dealdetail(href):
    lisoup = BeautifulSoup(urlopen(href))
    b = lisoup.findAll(id=re.compile('content_Div'))
    strong = lisoup('strong')
    c = lisoup.findAll(text=re.compile(u'白羊座'))
    for content in c:
        print u(content, 'utf-8')
    #print u(strong[0].text, 'utf-8')


def deal(href):
    soup = BeautifulSoup(urlopen(href))
    for strong in soup('strong'):
        print strong.text
        if strong.nextSibling:
            a = strong.nextSibling.split('<br/>')
            print a
            #print strong.nextSibling
        #if strong.nextSibling.contents[0]:
        #    print strong.nextSibling.contents[0].nextSibling
        #print u(strong.contents, 'utf-8')


def u(s, encoding):
    if isinstance(s, unicode):
        return s
    else:
        return unicode(s, encoding)


if __name__ == '__main__':
    main()
