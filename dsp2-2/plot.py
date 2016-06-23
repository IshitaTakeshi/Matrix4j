from os.path import join, expanduser

#from matplotlib.font_manager import FontProperties
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
from matplotlib import image

from skimage.color import rgb2gray
import numpy as np


#home = expanduser("~")
#font = FontProperties(fname=join(home, ".fonts", "ipag.ttf"), size=28)


def plot(matrix):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    assert(matrix.shape[0] == matrix.shape[1])
    N = matrix.shape[0]

    for i, row in enumerate(matrix):
        xs = np.arange(N)

        cs = row / np.max(matrix)
        zeros = np.zeros(len(cs))
        cs = np.vstack((cs, zeros, zeros)).T
        cs = 1 - np.abs(cs)
        ax.bar(xs, row, zs=i, zdir='y', color=cs, width=0.35, alpha=0.4)

    ax.set_xlabel('Row')
    ax.set_ylabel('Column')
    ax.set_zlabel('Value')
    #ax.set_title(description)
    ax.view_init(30, 30)
    plt.show()

matrix = np.loadtxt("dct")
plot(matrix)
image = image.imread("samp.bmp")
image = rgb2gray(image)
plot(image)
