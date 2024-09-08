PYSPARK_PYTHON=./ANACONDA/dev/bin/python
cd $SPARK_HOME
./bin/spark-submit \
        --conf spark.yarn.appMasterEnv.PYSPARK_PYTHON=./ANACONDA/dev/bin/python \
        --master yarn-cluster \
        --files $HIVE_HOME/conf/hive-site.xml \
        --archives /home/badou/Documents/code/pyspark/dev.zip#ANACONDA \
        /home/badou/Documents/code/pyspark/lcs.py
