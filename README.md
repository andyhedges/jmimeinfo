#jmimeinfo

jimimeinfo is a library for detecting the mime type from both the file name and the content of the file. It supports the database format of the [shared-mime-info] project.

##History

This library was first released under the GPL about 8 years ago on Sun Microsystems java.net code sharing portal. Sadly this seems to be no longer and so due to popular demand I'm re-releasing it here on github. I've also changed the license to the more permissive MIT license so that it can be used more widely.

##Plans

TBD

##Dependencies

Other than a JRE and the freedesktop database which you'll need to source yourself there are no dependencies.

Earlier versions were dependent on Apache Jakarta ORO, this is no longer true.

##Usage

You'll need a copy of the database on your class path as well as the jmimeinfo jar file.

The following will look at the contents of the file to determine it's type

```java
MagicFile magic = MagicFileFactory
					.create(IoUtil.getClasspathUrl("share/mime/magic"));

String mime = magic.match(IoUtil.getClasspathFile("data/test.flv"));
System.out.println("This file is of " + mime + "mime-type");
```

The following will look at the file name

```java
GlobsFile globs = GlobsFileFactory
					.create(IoUtil.getClasspathUrl("share/mime/globs"));
String mime = globs.match(IoUtil.getClasspathFile("data/test.flv"));
System.out.println("This file is of " + mime + "mime-type");
```

The following takes a hybrid approach

```java
MimeInfo mimeInfo = MimeInfoFactory.create();
String mime = mimeInfo.getMimeType(IoUtil.getClasspathFile("test.tar.gz"));
System.out.println("This file is of " + mime + "mime-type");
```

So far I haven't tested this against a modern version of the mime database from freedesktop. Please treat it as alpha code (even though it's 8+ years old).

[shared-mime-info]: http://freedesktop.org/wiki/Software/shared-mime-info/
