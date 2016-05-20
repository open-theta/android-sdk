package net.adsplay.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

public class Assets {
	public static final String exit = "iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAA2hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6 eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0 NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEu MC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVz b3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1N Ok9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpGRUU5MjA4OTBDMjA2ODExODA4M0YyQ0E4QjA4 M0I1MCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo2MzBFQTlFQTYxMjAxMUUzQjhCRkRDNTJC NjI0NTY5OCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2MzBFQTlFOTYxMjAxMUUzQjhCRkRD NTJCNjI0NTY5OCIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChNYWNpbnRv c2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6QTlBRDI1 MTgxMDIwNjgxMTgwODNGMkNBOEIwODNCNTAiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RkVF OTIwODkwQzIwNjgxMTgwODNGMkNBOEIwODNCNTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRm OlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6uekVTAAADwElEQVR42uyZXUgV QRTHvX5lGZVCWkpUWlhU+HD9qAcRNIoyQaV6sYfoQYKw8qUgK4LqWmJU9JhW0AfkQ4UFERFCL2Jh BqVkIopkaqIWpVh+bP+h/4Xltrt3Zu/erLgDP+S6Z878d3b2zDmzLk3Twv615gqJDokOiTZsySAL ZIMNYAmIBZNgFHSCFtAMWsHMbIl2gW2gDGwHUfz/CBgE4yASxIMkEMHr7eAmuAU+2BpZiLZBPmjS frUf4C7YA9aAuSCCdmJSokEi2Ao8oJP9PoNjtFcaX1VsDKjhoBPgDFiu6EPcRBF4QT+tIDNYouPB Ew70EKy2+ZT04g+B75yAYqdFLwYvKfgEH3uYQ2SBbvoudUq0WBJP6fSAg2L1rADvwBTY7IToCxR8 MkiCvaSBIdAPkgMRnUvBj4Is2Esxx7ttV7QIW81gjI/PzE6ErF2SorLBRj82Nyg8147oAnY+bWET Be7QrsqPmAw+/mGKt1rfE4xUyqIbwDhIMrm+gI9R36osBPfr7AZBjsXYdbRbryI6ifGz3sLxbs24 eXzsMn0Ee1sjiDTxnUObShXRJRJxM5ozayXcDT4aXG9jtDDzPYc3+lxF9Dk6T5N4ucyE1+o2DX1r BykSfu+BL2CRrGixnkcVkhmPJtfa/UQiPWfZZ52s6BYOEK4QY8UgMxaC34CVCv72s99vL2y4ScYq EvgxxWS9Elw3uTYECkG3gr+v/Bvje8FMtMak3qUwiBvkmFxbCPYqpvreomJGtggQb20vNw+ZRyni cJ/EmvYoLI8j7JMlu6avMeNKkHDuNonDwybCqyRFXwWTRkWGWYdyDpAnMcNGgt+CVeBUADP+CnQZ bUBWYjSWU2Y2m8Ank40jRSIc1uhqSV/E7E5zO5feXMTddbAIjbIQPSK5cRhtQBctQupB2hSoJkxH 2bHQT6o5ILlx6Ge82k960MFAME9VdCLL/BY/UUQE/8cgVWKdiiVxyY/NPt5Yhd3K5TgdlP+hymUp X+wupr62RM8Hr5mUZwRZsKjwH3CSigItbN0suXoUkh07VFPwZafOPXbS4XujrMsBztP/fb6Ijp0w lep2uhIH13A9/YqlERuMs7wtuh2wNoCjMXEAVMZaUWNEkc1zbJ2aLtNV4NPMU/IoxKpfOLf2Cp4m aYwSyk8tkPPpfHAY7ODvftAE2sAA82FxJp0AUnngnk7bHnAF1IEvs/ElIJ3CxcH6WhBnYDMFekEj aADPWGT8Fd9c4vgpI44VhxD7jTPfF+hni9CHopDo/1X0TwEGAMn4kfWfS4oUAAAAAElFTkSuQmCC";
	public static final String info = "iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAA2hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6 eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0 NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEu MC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVz b3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1N Ok9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpGQkU5MjA4OTBDMjA2ODExODA4M0YyQ0E4QjA4 M0I1MCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpDNDMxMTM3QjYxMjAxMUUzQjhCRkRDNTJC NjI0NTY5OCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpDNDMxMTM3QTYxMjAxMUUzQjhCRkRD NTJCNjI0NTY5OCIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChNYWNpbnRv c2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDY4MDEx NzQwNzIwNjgxMTgwODNBRjJCNzQzNDlDQ0IiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RkJF OTIwODkwQzIwNjgxMTgwODNGMkNBOEIwODNCNTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRm OlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5stwd/AAADTUlEQVR42uyZXUgU URTHnU1NzI9Wi1y31jCiNfMjwhL6sqSHSOwhInoq+vA1qtdAeq8ee4p6KKynCvIhsEwLEgrKfFA3 MzMrWc20D63NaPvf+g8syyYz584oxV74McvOnHv+c++dueecMaLRaMq/1oyk6KRod0Xng7WgAqwB PrAAfAOjoAd0kqG5Fl0DjoA6kEuRA2Ccv9P5fyDmfBu4CG6CabFqJdomW0Fb9E97Dc6CWrAYpMVd 6wF5YD04Bbpo1w32CXz/xs7F88E5Ou0B+0GGTYcGb7CV/dwAPrdEF4IHdNQIMqWjFMMB8AUMgWqn RQdAL5gAOxwQG0uQS+Yr2OyUaC87fQ/KHBZssgg85qCUOSH6OpgGG1wSbFIABkAILNQRfZhr+KDL gk2q6O+8VLSasjFwS1PEaps2pym8SiK6kcarhIJLaT8O8m3YZYM3oNmu6CwwApo0RtnPDaSV/dmx Pc4bLrEjuo5GWxxYp4bwoYxwqVgWfQEMC3a7WHL4xkkX2t8BT/523hMXiswDG0EHAxxpqwftjAAl rQWUgyWJTnoShJl+8FQzelwO3oIPQvtnHMCVVkR7QTZ4oSm6BIRBRGj/jkefFdEZPE5oii4C/Rr2 n3nMsiI6GneUNHXjheC5Rh8z+o8XbU5nrobDHE5rSKMPc4QnrYj+xAuLNRwuA2ngpUYf5loOWxE9 yoegUsOhuuEpMKbRh0qQf4I+K6J/gEegmompVLSarWEN0bWgN+YtMqPoFGbKAY3RDlKwdHPKA9tA s9UHUbW7XNtHhU5V/WNQY5T3gExwzW4J4Qz4zvzQboDUwIxbEnOojL8PtEviaRVaToIrs5S1xIel NdJ06yQ72D1LgoOc3SadHDEV3AMf2aGbglXG0smQuEA3G18KBkG/YH1bRRV+bjPr3+RUsaaUI/AK rHNYsC+mRFbvdFksyCpThG8HwwHBO5nEqtLYLrcKkCqrvspRuQ+2C8WW82FTrYMz6VrV1GQvM+0o 87gToILv2ETXq/LvCnAItNAuDI4lKA1bQlpUT+XO1cAt12BEFmLQNcW42suUqYh26mvAJXCZxfc5 +3xRzGS4ktGZn9uwis1HQDdzvoegK/mhKCk6Kfo/Ef1LgAEA1OTZevb7RacAAAAASUVORK5CYII=";
	public static final String pause = "iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAA2hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6 eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0 NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEu MC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVz b3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1N Ok9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpGOUU5MjA4OTBDMjA2ODExODA4M0YyQ0E4QjA4 M0I1MCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDpDNDMxMTM3NzYxMjAxMUUzQjhCRkRDNTJC NjI0NTY5OCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDpDNDMxMTM3NjYxMjAxMUUzQjhCRkRD NTJCNjI0NTY5OCIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChNYWNpbnRv c2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDM4MDEx NzQwNzIwNjgxMTgwODNBRjJCNzQzNDlDQ0IiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RjlF OTIwODkwQzIwNjgxMTgwODNGMkNBOEIwODNCNTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRm OlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7tuMs8AAADCElEQVR42uzZW0gU URzHcddVu4hlFwvTsKToZhZRPlVQgdFDQReyQui1XgLBSirwQXqLwvC1EKIgrMgKgkIfoqALimFa CEWGLRSoWG5Cptt36Le0iO7OdQdhD3wY0XHmNztzzpzz30AkEkmbbi09bRq2VOhU6Dgtw6XjrMdW bMFq5CETP9GHdrzGc/Q7PVnAwegxG8dwEhvwB53owSBGkY18lGIpwmhGA17aTm2EtqECHzGGuziA BXH2z0QJatAT+dduocjO+a3+Qw4addLbWGfjpFk4jq8YxEEvQ+fjDYZwyOYdijUP1/UBVHsReiG6 0Is1LgSOVaPgZ90MbTyPrQhhmcuBo04r+BG3QtfpgGUeBY66hhEUOw1dauHWGRdVjuWTdLxd+ltu gk7+GQ+dhn6gISrLROh2XWDthN/nRf637QmOsd/MfvFe42uxF3X4bWLI/6XtxH2Nt9ewfh5LcIxm vaDO2J17VOrNdi+J04px1GM3Cu2ENj7l+3r1JrM9Vvhyq6GXYCVafJjEhfBBEzBLoVcZkyl0+zT7 bEOJ1dCLFTrkU+hezQrTrYTOVuiwT6GNeXgOglZCj2sb9Cl0MN7wOFXoIY2vc30KPR8DWliYDt2n bZFPoY2B4JM+ONOh32MEm3x6NDZrXWnp8TA6wiu9YJLdSjRytNp5I97BTh8ekaNasT+zGzqs1Xay Wi5OoDHecBsvtNF7r6BKtytRm6Ft5sQyBWaZLA5Va9+rTipMl3WrGkyEfoeOmJEn2kbVPzrUV6Zq Rm3kPC7ii9O6xx5NzKs8XGrNQTfazCw4zB70goJXehA4Gy3oxwq36x71Cn7KxcAFeIEfVhbOVk9S q+A3VbxxEnifyhLGYnajl2Wx6OLzGwZwzkb4bXiki2/CIq9rebEr7EsIq0zWpOe9VOWuoPabiULs UL94q7CdKlraOn/A4RdFBajAYZRpJT6i2VlEQ2qW5ufGIvkpbuDJVDM4r+vTk60rjbG2WFPLDI3L 39GluvWwGycKpL6SS4VOhU6FttX+CjAAgpoINtDHo/4AAAAASUVORK5CYII=";
	public static final String play = "iVBORw0KGgoAAAANSUhEUgAAAC0AAAAtCAYAAAA6GuKaAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJ bWFnZVJlYWR5ccllPAAAA2hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdp bj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6 eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0 NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJo dHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlw dGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEu MC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVz b3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1N Ok9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpGNEU5MjA4OTBDMjA2ODExODA4M0YyQ0E4QjA4 M0I1MCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo2MzBFQTlFNjYxMjAxMUUzQjhCRkRDNTJC NjI0NTY5OCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2MzBFQTlFNTYxMjAxMUUzQjhCRkRD NTJCNjI0NTY5OCIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ1M2IChNYWNpbnRv c2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDI4MDEx NzQwNzIwNjgxMTgwODNBRjJCNzQzNDlDQ0IiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RjRF OTIwODkwQzIwNjgxMTgwODNGMkNBOEIwODNCNTAiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRm OlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz77Lxw7AAADm0lEQVR42uyZXUgU URiG1dW1H0szrazwF9O0ItmEDBOW8qKopC66qKiIgiDKLkKCfgiJIArSLuyiQsoMIugiMAgqCylE CAMtgpJAS/JvLY2ldpXtPfQOLTK7e2bmjCj4wcOiO3O+d2bOfud7z0QHAoGo6RYxUdMwZkRPVsQq HCsOpIMlIIH/84I+0A1+TxXR88EWsAuUgWTgnHCMHwyDVvAINIFBK0mjTVaPRHAMVIJF4At4AdrB J+ABYuAFIBusBW6QD0ZAHajhUzAeQrRBtoOuwL94ANwgVuI8B9gA6nnud7DXRH5DosVTucyELaDY TEKyEjRxrFsg3g7RTtDAJBck76wMxznmE5CgWvRtDn5IkdhgdoBx3nmnKtGnKfiIDYI1KpjjugrR JRzsqo2CNU4xV0WkY8OVPFHD21h3i1hv9WI2mMVabDWaQRZYDUbNlLx9vHJ3hCvPB+/BfgU/0FXM WWVmeoia2gmaJRLlBP7HM1BmUXgja/gco6LXU8ROSdH+IOF+VpsVJkWXRJrboU68AjyStXOiaC0G wHmQZFC0gyvufaOixdR4KJkklGgtOjnfYwwIrwF9IE7ve71+OhVksHKoiEJwBzxlJygTbWzEsmVN wHIwF3xU3LtvZid4E+RFOLaLn5myopNEywp6bTAdDnAYtIBzbF31QmtZU2RFxwe5DrtCTMFq8BYU 63zvDVq4pJyL3wYrphfCLFwDn0NYNxFjsqK1q0y2SewQuAFqw9iuRDqfEdnp0Q98IEex2HHQAEo5 n8P5xAz+rr7J3ukeerw1CgW/BBfBcwNl0scpJCXaR+fsViBWzNdLoJHjykY5TbLHSJd3gKtZockV cQhUgxQTvcdS4ANnjC7jqcDLHkRG9BjFCtt0FxRY6PJOcqxcM86lDvziBYQ7Lo9JRBtbbrEtFe1o N3hs1m5l8jHVRki0jIbXqcByVfEGuKx4xLMcZOMkeMQCTq86Kx4xit6vmXWzOFTdVBCi33nDFsIF fljdFssC/eAdWGzDHZ5Hm/YHrFO5WSPs1yjosGCj9EgDrzgtttqxlyf27nrBT7BHgeBt4CvH22TX BqQgPWjjUHyWmhDr4m6riNeSC5gl0RoHQQ8Tt4JKJk8MMWfFlDrKWq6tmCcM+kbp6hFpY303nYiL rmSQDdcI/xa2LY2vNER0gHpwDwxM9puAiZFL4UX0fwvZWg6zU2unS/mgIpkq0TOv5GZET5X4K8AA /T7sDkDoeaAAAAAASUVORK5CYII=";
	
	public static Drawable getDrawableFromBase64(Resources resources, String encodedString) {
		byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return new BitmapDrawable(resources, bitmap);
	}
}
