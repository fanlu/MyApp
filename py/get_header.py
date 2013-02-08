#!/usr/bin/env python
#!/usr/bin/env python
import urllib

def main():
    for x in range(200):
        urllib.urlretrieve("http://www.chuchujie.com/huanletao/images/header/uh%s.jpg" % (x + 1), "/www/i1/images/header/%s.jpg" % (x + 1))
    pass

if __name__ == '__main__':
    main()
