# rmm-services-server-app

1. Download project ``git clone https://github.com/kclegg/rmm-services-server-app.git``
2. Checkout branch origin/master
3. IDE Steps:

 * Mark java under `src\main\java` as "Sources Root"
 * Mark resources under `src\main\resources` as "Resources Root"
 * Set project sdk & language levels to java 11
 * Declare as a Maven project (right click pom.xml) & pull all dependencies.
 * Add a new run configuration (Application) main class: rmm.RmmServerApp

4. Build then, run **RmmServerApp**
5. Go to local swagger page to test endpoints http://localhost:8080/swagger-ui/index.html
6. After clicking "Execute" on any endpoint enter the following credentials & click "sign in"

   username: *user*
   
   password: <*generated security password*> - from console output