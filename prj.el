(jde-project-file-version "1.0")
(jde-set-variables
 '(jde-run-working-directory "e:/gits/ztools/")
 '(jde-compile-option-directory "e:/gits/ztools/bin/")
 '(jde-run-option-boot-classpath (quote ("append" "e:/gits/ztools/bin/" "e:/gits/ztools/lib/"))))
(jde-set-variables
 '(jde-project-name "ztools")
 '(jde-vm-path "d:/Java/jdk1.6/bin/java")
 '(jde-jdk-registry (quote (("10.0-b22" . "D:/Java/jdk1.6"))))
 ; classpath
 '(jde-global-classpath 
   (quote 
    ("e:/gits/ztools/lib/"
     "e:/gits/ztools/bin/"
     "$ANT_HOME/lib/")
    ))
 '(jde-compile-option-sourcepath (quote ("e:/gits/ztools/src" 
                                         "e:/gits/ztools/test"))
 '(jde-run-working-directory "e:/gits/ztools/")
 '(jde-sourcepath (quote ("e:/gits/ztools/src/" "e:/gits/ztools/test"))
 ;'(jde-run-application-class "e:/gits/ztools/bin")
 ;'(jde-run-working-directory "e:/gits/ztools")
 '(jde-compile-option-directory "e:/gits/ztools/bin/")
 '(jde-compile-option-encoding "utf-8")
 '(jde-build-function (quote (jde-ant-build)))
 '(jde-ant-enable-find t)
 '(jde-ant-read-target t)
 '(jde-ant-home "$ANT_HOME")
 '(jde-ant-invocation-method (quote ("Ant Server")))
 ;'(jde-ant-user-jar-files (quote ("")) ; 这里对应eclipse中add build里的jars
 ;'(jde-vm-path "d:/Java/jdk1.6/bin/java")
 ;'(jde-run-application-class "e:/gits/ztools/bin")
)
