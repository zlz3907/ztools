(jde-project-file-version "1.0")
(jde-set-variables
 '(jde-project-name "ztools")
;; '(jde-jdk-registry (quote (("11.0-b16" . "D:/Java/jdk1.6"))))
;; '(jde-jdk-registry (quote (("11.0-b16" . "D:/Java/jdk1.6/"))))
 ;; classpath
 '(jde-global-classpath 
   (quote 
    ("e:/home/lizhi/workspace/2012/ztools/lib/*"
     "e:/home/lizhi/workspace/2012/ztools/bin/"
     "$ANT_HOME/lib/*")
    ))
 ;; 在编译时用到
 '(jde-compile-option-sourcepath 
   (quote ("e:/home/lizhi/workspace/2012/ztools/src" 
	   "e:/home/lizhi/workspace/2012/ztools/test")))
 '(jde-compile-option-classpath 
   (quote ("e:/home/lizhi/workspace/2012/ztools/lib/*" 
	   "e:/home/lizhi/workspace/2012/ztools/bin/")))
 ;; Junit
 '(jde-junit-working-directory "e:/home/lizhi/workspace/2012/ztools/")
 '(jde-run-working-directory "e:/home/lizhi/workspace/2012/ztools/")
 '(jde-sourcepath 
   (quote ("e:/home/lizhi/workspace/2012/ztools/src/" 
	   "e:/home/lizhi/workspace/2012/ztools/test")))
 ;;'(jde-run-application-class "e:/home/lizhi/workspace/2012/ztools/bin")
 ;;'(jde-run-working-directory "e:/home/lizhi/workspace/2012/ztools")
 '(jde-compile-option-directory "e:/home/lizhi/workspace/2012/ztools/bin/")
 '(jde-compile-option-encoding "utf-8")
 '(jde-build-function (quote (jde-ant-build)))
 '(jde-ant-enable-find t)
 '(jde-ant-read-target t)
 '(jde-ant-home "$ANT_HOME")
 '(jde-ant-invocation-method (quote ("Ant Server")))
 ;;'(jde-ant-user-jar-files (quote ("")) ; 这里对应eclipse中add build里的jars
 ;;'(jde-vm-path "d:/Java/jdk1.6/bin/java")
 ;;'(jde-run-application-class "e:/home/lizhi/workspace/2012/ztools/bin")
 )
