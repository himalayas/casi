import spock.lang.Specification

/**
 * User: Think
 * Date: 13-1-25
 * Time: 下午11:42
 */
class TestClass extends Specification {
    def "test no Test start mathod"(){
        print System.getProperty("user.dir")
        expect:
            a>0
        where:
            a|b|c
            1|2|3
            5|4|1
            5|2|9
            5|2|0
    }
}