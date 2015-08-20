package oo.bootcamp.practice.unittest.day2

import spock.lang.Specification

/**
 * Created by Xuesong Mei on 8/14/15.
 */
class MyFirstSpec extends Specification {
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }

    def 'a simple test'() {
        given:
        List<Integer> list = new ArrayList<>()
        when:
        list.add(1)
        then:
        1 == list.get(0)
    }

    void "test something"() {
        expect:"fix me"
        true == true
    }
}
