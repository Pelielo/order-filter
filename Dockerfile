FROM hseeberger/scala-sbt:8u265_1.4.6_2.13.4

COPY . app
RUN cd app && sbt package

ENTRYPOINT ["scala", "/root/app/target/scala-2.13/order-filter_2.13-0.1.jar"]

