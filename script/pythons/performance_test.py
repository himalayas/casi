__author__ = 'himalayas'
import cProfile
import hotshot, hotshot.stats, test.pystone
def test():
    prof = hotshot.Profile("stones.prof")
    benchtime, stones = prof.runcall()
    prof.close()
    stats = hotshot.stats.load("stones.prof")
    stats.strip_dirs()
    stats.sort_stats('time', 'calls')
    stats.print_stats(20)

if __name__=="__main__":
    test()