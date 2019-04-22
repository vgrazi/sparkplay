package com.vgrazi.play

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
object WordCounter {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("file:///C:/programs/spark-2.4.1-bin-hadoop2.7/README.md")
    val tokenizedFileData=textFile.flatMap(line=>line.split(" "))
    val countPrep=tokenizedFileData.map(word=>(word, 1))
    val counts=countPrep.reduceByKey((accumValue, newValue)=>accumValue+newValue)
    val sortedCounts=counts.sortBy(kvPair=>kvPair._2, false)
    sortedCounts.saveAsTextFile("file:///c:/dev/spark-course/ReadMeWordCountViaApp")
  }
}
